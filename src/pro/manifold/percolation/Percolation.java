package pro.manifold.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF sites;
    private final int size;
    private final boolean[] opened;
    private final int topVirtualSite;
    private final int bottomVirtualSite;
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private final int size1D;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n must be at least 1");
        }
        size = n;
        size1D = n * n;
        sites = new WeightedQuickUnionUF(n * n + 2);
        opened = new boolean[n * n + 2];
        topVirtualSite = 0;
        bottomVirtualSite = xyTo1D(size, size) + 1;
    }

    public void open(int row, int col) {
        validate(row, col);
        final int xy = xyTo1D(col, row);
        opened[xy] = true;
        if (row == 1) {
            sites.union(sites.find(topVirtualSite), sites.find(xy));
        }
        if (row == size) {
            sites.union(sites.find(bottomVirtualSite), sites.find(xy));
        }
        for (int[] direction : DIRECTIONS) {
            int xy1 = xyTo1D(col + direction[0], row + direction[1]);
            if (xy1 >= 1 && xy1 <= size1D && opened[xy1]) {
                sites.union(sites.find(xy), sites.find(xy1));
            }
        }
    }

    public boolean percolates() {
        return sites.connected(topVirtualSite, bottomVirtualSite);
    }

    public boolean isFull(int row, int col) {
        return sites.connected(topVirtualSite, xyTo1D(col, row));
    }

    public boolean isOpen(int row, int col) {
        return opened[xyTo1D(col, row)];
    }

    protected int xyTo1D(int x, int y) {
        return x + (y - 1) * size;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > size) {
            throw new IndexOutOfBoundsException("row index " + row + " out of bounds");
        }
        if (col < 1 || col > size) {
            throw new IndexOutOfBoundsException("col index " + col + " out of bounds");
        }
    }
}
