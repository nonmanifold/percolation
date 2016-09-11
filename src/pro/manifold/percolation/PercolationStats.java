package pro.manifold.percolation;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %d", stats.mean());
        StdOut.printf("stddev                  = %d", stats.stddev());
        StdOut.printf("95% confidence interval = %d, %d", stats.confidenceLo(), stats.confidenceHi());
    }

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0;
    }

}
