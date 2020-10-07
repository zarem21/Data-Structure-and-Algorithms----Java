package kdtree;

import heap.NaiveMinPQ;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class KDTreeTest {

    @Test
    public void basicTest() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(100, 300.9);
        Point p4 = new Point(-2.9, 4.2);
        Point p5 = new Point(1, 2.0);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
//        points.add(p3);
        points.add(p4);
//        points.add(p5);
        NaivePointSet test = new NaivePointSet(points);

        assertEquals(p2, test.nearest(3.0, 4.0));
    }
}
