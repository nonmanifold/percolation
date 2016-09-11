import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class PercolationTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void xyTo1D() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Percolation perc = new Percolation(10);

        Method xyTo1D = perc.getClass().getDeclaredMethod("xyTo1D", int.class, int.class);
        xyTo1D.setAccessible(true);
        assertEquals("xyTo1D(1, 1) should return 1", 1, xyTo1D.invoke(perc, 1, 1));
        assertEquals("xyTo1D(10, 10) should return 100", 100, xyTo1D.invoke(perc, 10, 10));
    }

    @Test
    public void throwOnIlligalN() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        new Percolation(0);
    }

    @Test
    public void throwWhenOpenOutsideCols() throws Exception {
        thrown.expect(IndexOutOfBoundsException.class);
        Percolation perc = new Percolation(1);
        perc.open(1, 10);
    }

    @Test
    public void throwWhenOpenOutsideRows() throws Exception {
        thrown.expect(IndexOutOfBoundsException.class);
        Percolation perc = new Percolation(1);
        perc.open(10, 1);
    }
    @Test
    public void throwWhenIsFullOutsideRows() throws Exception {
        thrown.expect(IndexOutOfBoundsException.class);
        Percolation perc = new Percolation(1);
        perc.isFull(10, 1);
    }

    @Test
    public void simplePercolation1() throws Exception {
        Percolation perc = new Percolation(1);
        assertFalse("initally does not percolate", perc.percolates());
        perc.open(1, 1);
        assertTrue("should be filled at 1,1", perc.isFull(1, 1));
        assertTrue("percolates after element added", perc.percolates());
    }

    @Test
    public void simplePercolation2() throws Exception {
        Percolation perc = new Percolation(2);
        assertFalse("initally does not percolate", perc.percolates());
        perc.open(1, 1);
        assertTrue("should be filled at 1,1", perc.isFull(1, 1));
        assertTrue("should be open at 1,1", perc.isOpen(1, 1));
        perc.open(2, 2);
        assertFalse("should not be filled at 2,2", perc.isFull(2, 2));
        assertTrue("should not be open at 2,2", perc.isOpen(2, 2));
        assertFalse("does not percolate when there is only diagonal", perc.percolates());
        perc.open(1, 2);
        assertTrue("percolates after two elements added", perc.percolates());
    }

}