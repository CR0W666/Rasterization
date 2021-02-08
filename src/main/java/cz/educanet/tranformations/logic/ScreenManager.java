package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ScreenManager {

    private Set<Coordinate> selectedPoints = new HashSet<>();

    public void select(Coordinate coordinate) {
        selectedPoints.add(coordinate);
    }

    public void unselect(Coordinate coordinate) {
        selectedPoints.remove(coordinate);
    }

    public boolean isSelected(Coordinate coordinate) {
        return selectedPoints.contains(coordinate);
    }

    public Set<Coordinate> getSelectedPoints() {
        return selectedPoints;
    }

    public boolean isFilledIn(Coordinate coordinate) { // TODO: Implement this
        Coordinate t0, t1, t2;
        ArrayList<Coordinate> ts = new ArrayList<>();

        if (selectedPoints.size() == 3) {

            for (Coordinate i : selectedPoints) {
                ts.add(i);
            }
            t0 = ts.get(0);
            t1 = ts.get(1);
            t2 = ts.get(2);

            ts.clear();
            double dx = coordinate.getX() - t2.getX();
            double dy = coordinate.getY() - t2.getY();
            double a = (t1.getY() - t2.getY()) * dx + (t2.getX() - t1.getX()) * dy;
            double det = (t1.getY() - t2.getY()) * (t0.getX() - t2.getX())
                    - (t2.getX() - t1.getX()) * (t2.getY() - t0.getY());
            double minD = Math.min(det, 0);
            double maxD = Math.max(det, 0);
            if (a < minD || a > maxD)
                return false;
            double b = (t2.getY() - t0.getY()) * dx + (t0.getX() - t2.getX()) * dy;
            if (b < minD || b > maxD)
                return false;
            double c = det - a - b;
            if (c < minD || c > maxD)
                return false;
            return true;

        } else
            return false;

    }
}
