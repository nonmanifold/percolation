package pro.manifold.percolation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class PercolationStatsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwOnIlligalN() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        PercolationStats stats = new PercolationStats(0, 100);
    }

    @Test
    public void throwOnIlligalTrials() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        PercolationStats stats = new PercolationStats(10, 0);
    }

    @Test
    public void test200x100() throws Exception {
        PercolationStats stats = new PercolationStats(200, 100);
        double mean = stats.mean();
        assertTrue("mean, ~0.59", closeTo(0.59, mean, 0.05));
        double stddev = stats.stddev();
        assertTrue("stddev, ~0.008", closeTo(0.01, stddev, 0.01));
        double confidenceLo = stats.confidenceLo();
        assertTrue("95% confidence interval low >0.55", confidenceLo >= 0.55);
        double confidenceHi = stats.confidenceHi();
        assertTrue("95% confidence interval high <0.65", confidenceHi <= 0.60);
    }

    @Test
    public void test2x100000() throws Exception {
        PercolationStats stats = new PercolationStats(2, 100000);
        double mean = stats.mean();
        assertTrue("mean, ~0.66", closeTo(0.66, mean, 0.05));
        double stddev = stats.stddev();
        assertTrue("stddev, ~0.11", closeTo(0.11, stddev, 0.02));
        double confidenceLo = stats.confidenceLo();
        assertTrue("95% confidence interval low >0.60", confidenceLo >= 0.60);
        double confidenceHi = stats.confidenceHi();
        assertTrue("95% confidence interval high <0.70", confidenceHi <= 0.70);
    }

    private boolean closeTo(double expected, double actual, double alpha) {
        return Math.abs(expected - actual) <= alpha;
    }
}