package kdtree;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreePointSetTest {

    @Test
    public void randomtest() {
        // Random seed ensures that each test run is reproducible
        int seed = 373; // or your favorite number
        Random random = new Random(seed);
        List<Point> points = new ArrayList<>();
        // Create a new ArrayDeque (reference implementation)
        NaivePointSet expectedPoint;
        // Create a new LinkedDeque (testing implementation)
        KDTreePointSet testingPoint;

        // Add 1000000 random integers to both the expectedPoint and testingPoint implementations
        for (int i = 0; i < 100000; i += 1) {
            double x = random.nextInt();
            double y = random.nextInt();
            Point p = new Point(x, y);
            points.add(p);

        }
        expectedPoint = new NaivePointSet(points);
        testingPoint = new KDTreePointSet(points);

        // Check that testingDeque matches expectedDeque on all 1000000 integers
        for (int i = 0; i < 100000; i += 1) {
            double x = random.nextInt();
            double y = random.nextInt();

            Point p = expectedPoint.nearest(x, y);
            Point p1 = testingPoint.nearest(x, y);

            assertEquals("Failed on iteration " + i, p, p1);
            // To debug a particular iteration, set a conditional breakpoint
            // Add a regular breakpoint but right click and set a condition like i == 57893
        }

    }

    @Test
    public void buildTest() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(100, 300.9);
        Point p4 = new Point(-2.9, 4.2);
        Point p5 = new Point(1, 2.0);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        KDTreePointSet test = new KDTreePointSet(points);
    }

    @Test
    public void basicTest() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(-100, 0);
        Point p5 = new Point(-100, 10);
        Point p6 = new Point(10, 4.2);


        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        KDTreePointSet test = new KDTreePointSet(points);
        NaivePointSet actual = new NaivePointSet(points);
        Point pactual = actual.nearest(-100, 9);
        Point ptest = test.nearest(-100, 9);
        TestCase.assertEquals(pactual, ptest);
    }

    @Test
    public void robustTest() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(-100, 0);
        Point p5 = new Point(-100, 10);
        Point p6 = new Point(10, 4.2);
        Point p7 = new Point(-1, 1);
        Point p8 = new Point(-10, 4.2);
        Point p9 = new Point(-6, 8);
        Point p10 = new Point(6, 8);
        Point p11 = new Point(0, 0);
        Point p12 = new Point(1, 1);
        Point p13 = new Point(-1, 1);
        Point p14 = new Point(0, 0);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);
        points.add(p9);
        points.add(p9);
        points.add(p10);
        points.add(p11);
        points.add(p12);
        points.add(p13);
        points.add(p14);

        KDTreePointSet test = new KDTreePointSet(points);
        NaivePointSet actual = new NaivePointSet(points);
        Point pactual = actual.nearest(0.24, 1);
        Point ptest = test.nearest(0.24, 1);
        TestCase.assertEquals(pactual, ptest);
    }

}
