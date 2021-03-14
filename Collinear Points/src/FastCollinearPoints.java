import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        checkForException(points);

        Arrays.sort(points);

        int numPoints = points.length;

        List<LineSegment> segments = new LinkedList<>();


        for (Point pointP : points) {

            Point[] sortedBySlope = points.clone();
            Arrays.sort(sortedBySlope, pointP.slopeOrder());


            int q = 1;
            while (q < numPoints) {

                LinkedList<Point> possibilities = new LinkedList<>();
                Point pointQ = sortedBySlope[q];
                final double slopePQ = pointP.slopeTo(pointQ);

                do {
                    possibilities.add(sortedBySlope[q++]);
                } while (q < numPoints && pointP.slopeTo(sortedBySlope[q]) == slopePQ);

                if (possibilities.size() >= 3 && pointP.compareTo(possibilities.peek()) < 0) {
                    Point endPoint = possibilities.removeLast();
                    segments.add(new LineSegment(pointP, endPoint));
                }


            }
        }

        lineSegments = segments.toArray(new LineSegment[0]);

    }

    private void checkForException(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Null argument");
        } else {
            for (Point point : points) {
                if (point == null) {
                    throw new IllegalArgumentException("Point cannot be null");
                }
            }
            checkRepeatedPoint(points);
        }
    }

    private void checkRepeatedPoint(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Points cannot be repeated");
                }
            }
        }
    }

    // returns the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // returns the line segments
    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
