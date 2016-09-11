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
        assertTrue("mean, ~0.59", closeTo(0.59, stats.mean(), 0.02));
        assertTrue("stddev, ~0.008", closeTo(0.01, stats.stddev(), 0.01));
        assertTrue("95% confidence interval low >0.55", 0.55 >= stats.confidenceLo());
        assertTrue("95% confidence interval high <0.65", 0.60 <= stats.confidenceLo());
    }

    @Test
    public void test2x100000() throws Exception {
        PercolationStats stats = new PercolationStats(2, 100000);
        assertTrue("mean, ~0.66", closeTo(0.66, stats.mean(), 0.02));
        assertTrue("stddev, ~0.11", closeTo(0.11, stats.stddev(), 0.02));
        assertTrue("95% confidence interval low >0.60", 0.60 >= stats.confidenceLo());
        assertTrue("95% confidence interval high <0.70", 0.70 <= stats.confidenceLo());
    }

    private boolean closeTo(double expected, double actual, double alpha) {
        return Math.abs(expected - actual) <= alpha;
    }
}