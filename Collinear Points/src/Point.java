import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    // initializes a new point
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws the point to standard draw
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point to standard draw
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    //returns string representation of the point
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else if (this.x < that.x) {
            return -1;
        } else if (this.x > that.x) {
            return 1;
        } else if (equals(that)) {
            return 0;
        }
        return 0;
    }


    // returns slope between this point and the specific point
    public double slopeTo(Point that) {
        if (equals(that)) {
            return Double.NEGATIVE_INFINITY;
        }
        double denominator = (that.x - this.x);
        if (denominator == 0.0) {
            return Double.POSITIVE_INFINITY;
        }
        double numerator = that.y - this.y;
        return numerator / denominator;
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            return Double.compare(slope1, slope2);
        }
    }

    // returns the comparator that defines this ordering of points
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 10);
        Point p2 = new Point(10, 0);
        System.out.println(p1.slopeTo(p2));
    }
}

