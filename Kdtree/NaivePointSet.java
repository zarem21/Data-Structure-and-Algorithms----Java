package kdtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Naive nearest neighbor implementation using a linear scan.
 */
public class NaivePointSet implements PointSet {
    private List<Point> points;

    /**
     * Instantiates a new NaivePointSet with the given points.
     * @param points a non-null, non-empty list of points to include
     *               (makes a defensive copy of points, so changes to the list
     *               after construction don't affect the point set)
     */
    public NaivePointSet(List<Point> points) {
        this.points = new ArrayList<Point>();
        for (Point p: points) {
            this.points.add(p);
        }
    }

    /**
     * Returns the point in this set closest to (x, y) in O(N) time,
     * where N is the number of points in this set.
     */
    @Override
    public Point nearest(double x, double y) {
        Point nearest = points.get(1);
        for (Point p: this.points) {
            if (p.distanceSquaredTo(x, y) < nearest.distanceSquaredTo(x, y)) {
                nearest = p;
            }
        }
        return nearest;
    }
}
