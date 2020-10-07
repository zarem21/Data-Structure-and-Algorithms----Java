package kdtree;

import java.util.List;

public class KDTreePointSet implements PointSet {
    private Node head;

    /**
     * Instantiates a new KDTree with the given points.
     *
     * @param points a non-null, non-empty list of points to include
     *               (makes a defensive copy of points, so changes to the list
     *               after construction don't affect the point set)
     */

    // Referred to my CSE 143 QuestionsGame code
    public KDTreePointSet(List<Point> points) {
        int level = 0;
        for (Point p : points) {
            head = treeBuilder(p, head, level);
        }
    }

    private Node treeBuilder(Point point, Node node, int level) {
        if (node == null) {
            node = new Node(point.x(), point.y());
        } else if (level % 2 == 0) {
            if (node.x > point.x()) {
                node.left = treeBuilder(point, node.left, level + 1);
            } else {
                node.right = treeBuilder(point, node.right, level + 1);
            }
        } else {
            if (node.y > point.y()) {
                node.left = treeBuilder(point, node.left, level + 1);
            } else {
                node.right = treeBuilder(point, node.right, level + 1);
            }
        }
        return node;

        /*
        1. check if the point is smaller/bigger than root node
            a. if smaller, go left (treeBuilder(point, node.left))
            b. if bigger, go right (treeBuilder(point, node.right))
            ** must check the level to know how to compare (x or y). put level in method
        2. if (root == null) -- add the point
         */
    }


    /**
     * Returns the point in this set closest to (x, y) in (usually) O(log N) time,
     * where N is the number of points in this set.
     */
    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Point bestPoint = new Point(head.x, head.y);
        bestPoint = nearest(target, this.head, bestPoint, 0);
        return bestPoint;
    }

    private Point nearest(Point target, Node root, Point bestPoint, int level) {
        if (root == null) {
            return bestPoint;
        } else {
            double currentDistance = Math.sqrt(target.distanceSquaredTo(root.x, root.y));
            double bestDistance = Math.sqrt(target.distanceSquaredTo(bestPoint));
            // Checks first to see if the current point is closer to target and updates accordingly
            if (currentDistance < bestDistance) {
                bestPoint = new Point(root.x, root.y);
                bestDistance = currentDistance;
            }
            // Check to see if on even or odd level. To see if X or Y's will be compared
            if (level % 2 == 0) {
                if (root.x > target.x() && Math.abs(target.x() - root.x) < bestDistance) { // Go left && Check right (x)
                    bestPoint = nearest(target, root.left, bestPoint, level + 1);
                    bestPoint = nearest(target, root.right, bestPoint, level + 1);
                } else if (root.x > target.x() && Math.abs(target.x() - root.x) > bestDistance) { // Go left && NoCheck
                    bestPoint = nearest(target, root.left, bestPoint, level + 1);
                } else if (root.x < target.x() && Math.abs(target.x() - root.x) < bestDistance) {
                    bestPoint = nearest(target, root.right, bestPoint, level + 1);
                    bestPoint = nearest(target, root.left, bestPoint, level + 1);
                } else {
                    bestPoint = nearest(target, root.right, bestPoint, level + 1);
                }
            } else {
                if (root.y > target.y() && Math.abs(target.y() - root.y) < bestDistance) {
                    bestPoint = nearest(target, root.left, bestPoint, level + 1);
                    bestPoint = nearest(target, root.right, bestPoint, level + 1);
                } else if (root.y > target.y() && Math.abs(target.y() - root.y) > bestDistance) {
                    bestPoint = nearest(target, root.left, bestPoint, level + 1);
                } else if (root.y < target.y() && Math.abs(target.y() - root.y) < bestDistance) {
                    bestPoint = nearest(target, root.right, bestPoint, level + 1);
                    bestPoint = nearest(target, root.left, bestPoint, level + 1);
                } else {
                    bestPoint = nearest(target, root.right, bestPoint, level + 1);
                }
            }
        }
        return bestPoint;
    }

    private class Node {
        private double x;
        private double y;
        private int level;
        private Node left;
        private Node right;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

}
        