import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private final double[] xis;
    private final int n;
    private final int n2;

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %f\n", stats.mean());
        StdOut.printf("stddev                  = %f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = %f %f\n", stats.confidenceLo(), stats.confidenceHi());
    }

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials must be greater than 0");
        }
        this.n = n;
        this.n2 = n * n;
        xis = new double[trials];
        for (int t = 0; t < trials; t++) {
            xis[t] = fillTillPercolates();
        }
    }

    private double fillTillPercolates() {
        Percolation perc = new Percolation(n);
        int xi = 0;
        // initialize an array of two-element arrays with all the fillable cells indecies
        int[][] canBeBloked = new int[n2][];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                canBeBloked[row * n + col] = new int[]{row + 1, col + 1};
            }
        }
        int canBeBlokedCount = canBeBloked.length;
        while (!perc.percolates()) {
            int siteToFillIdx = StdRandom.uniform(canBeBlokedCount);
            int row = canBeBloked[siteToFillIdx][0];
            int col = canBeBloked[siteToFillIdx][1];

            //swap used element outside of working set:
            canBeBloked[siteToFillIdx] = canBeBloked[canBeBlokedCount - 1];
            canBeBlokedCount--; // move pointer to the working set far end
            perc.open(row, col);
            xi++;// advance counter
        }
        return xi / (double) n2;
    }

    // sample mean of percolation threshold
    public double mean() {
        double xiSum = 0;
        for (double xi : xis) {
            xiSum += xi;
        }
        return xiSum / (double) xis.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();
        double xiSumMinusMean = 0;
        for (double xi : xis) {
            xiSumMinusMean += (xi - mean) * (xi - mean);
        }
        return Math.sqrt(xiSumMinusMean / ((double) xis.length - 1));
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        double mean = mean();
        return mean - 1.96 * stddev() / Math.sqrt((double) xis.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = mean();
        return mean + 1.96 * stddev() / Math.sqrt((double) xis.length);
    }

}
