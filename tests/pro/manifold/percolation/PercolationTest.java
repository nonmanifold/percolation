package pro.manifold.percolation;

import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest {

    @Test
    public void xyTo1DTest() throws Exception {
        Percolation perc = new Percolation(10);
        assertEquals("xyTo1D(1, 1) should return 1", 1, perc.xyTo1D(1, 1));
        assertEquals("xyTo1D(10, 10) should return 100", 100, perc.xyTo1D(10, 10));
    }

}