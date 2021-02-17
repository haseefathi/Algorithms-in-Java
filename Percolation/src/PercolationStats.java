import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] results;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        this.trials = trials;

        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than 0");
        if (trials <= 0)
            throw new IllegalArgumentException("trials must be greater than 0");

        results = new double[trials];

        // creating the n*n grid and evaluating for each trial
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }

            double numOpenSites = p.numberOfOpenSites();
            double totalSites = (double) n * n;

            results[i] = numOpenSites / totalSites;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(results) - (1.96 * (stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(results) + (1.96 * (stddev()) / Math.sqrt(trials));
    }


    // test client
    public static void main(String[] args) {

        System.out.println("Enter n: ");
        int n = StdIn.readInt();
        System.out.println("Enter trials: ");
        int trials = StdIn.readInt();

        PercolationStats ps = new PercolationStats(n, trials);

        System.out.println("\nMean: " + ps.mean());
        System.out.println("StdDev: " + ps.stddev());
        System.out.println("95% Confidence Interval: [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");

    }
}
