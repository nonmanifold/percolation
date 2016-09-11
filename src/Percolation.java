import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private final WeightedQuickUnionUF sites;
    private final WeightedQuickUnionUF sitesTopOnly;
    private final int size;
    private final boolean[] opened;
    private final int topVirtualSite;
    private final int bottomVirtualSite;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n must be at least 1");
        }
        size = n;

        sites = new WeightedQuickUnionUF(n * n + 2);
        sitesTopOnly = new WeightedQuickUnionUF(n * n + 1);
        opened = new boolean[n * n + 2];
        topVirtualSite = 0;
        bottomVirtualSite = xyTo1D(size, size) + 1;
    }

    // open site (row i, column j) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        final int xy = xyTo1D(col, row);
        if (opened[xy]) {
            return;
        }
        opened[xy] = true;
        if (row == 1) {
            sites.union(sites.find(topVirtualSite), sites.find(xy));
            sitesTopOnly.union(sitesTopOnly.find(topVirtualSite), sitesTopOnly.find(xy));
        }
        if (row == size) {
            sites.union(sites.find(bottomVirtualSite), sites.find(xy));
        }
        for (int[] direction : DIRECTIONS) {
            int col1 = col + direction[0];
            int row1 = row + direction[1];
            int xy1 = xyTo1D(col1, row1);
            if (isInside(row1, col1) && opened[xy1]) {
                sites.union(sites.find(xy), sites.find(xy1));
                sitesTopOnly.union(sitesTopOnly.find(xy), sitesTopOnly.find(xy1));
            }
        }
    }

    // does the system percolate?
    public boolean percolates() {
        return sites.connected(topVirtualSite, bottomVirtualSite);
    }

    // is site (row i, column j) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return sitesTopOnly.connected(topVirtualSite, xyTo1D(col, row));
    }

    // is site (row i, column j) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return opened[xyTo1D(col, row)];
    }

    private int xyTo1D(int x, int y) {
        return x + (y - 1) * size;
    }

    private boolean isInside(int row, int col) {
        return !(row < 1 || row > size || col < 1 || col > size);
    }

    private void validate(int row, int col) {
        if (!isInside(row, col)) {
            throw new IndexOutOfBoundsException("row index or col index is out of bounds");
        }
    }
}
