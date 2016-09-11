package pro.manifold.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF sites;

    public Percolation(int n) {
        sites = new WeightedQuickUnionUF(n * n);
    }

    public void open(int i, int j) {

    }

    public boolean percolates() {
        return false;
    }

    public boolean isFull(int row, int col) {
        return false;
    }

    public boolean isOpen(int row, int col) {
        return false;
    }
}
