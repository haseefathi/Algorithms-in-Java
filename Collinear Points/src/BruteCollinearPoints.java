import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        checkForException(points);

        Arrays.sort(points);

        int numPoints = points.length;

        List<LineSegment> segments = new LinkedList<>();

        for (int p = 0; p < numPoints; p++) {
            Point pointP = points[p];
            for (int q = p + 1; q < numPoints; q++) {
                Point pointQ = points[q];
                double slopePQ = pointP.slopeTo(pointQ);
                for (int r = q + 1; r < numPoints; r++) {
                    Point pointR = points[r];
                    double slopePR = pointP.slopeTo(pointR);
                    if (slopePQ == slopePR) {
                        for (int s = r + 1; s < numPoints; s++) {
                            Point pointS = points[s];
                            double slopePS = pointP.slopeTo(pointS);
                            if (slopePQ == slopePS) {
                                segments.add(new LineSegment(pointP, pointS));
                            }

                        }
                    }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
