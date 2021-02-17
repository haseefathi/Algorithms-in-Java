import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    int N;
    WeightedQuickUnionUF grid;
    boolean[] openArray; //holds true value if the site is open..otherwise false
    int topIdx, bottomIdx;
    int numOfOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        } else {
            N = n;

            grid = new WeightedQuickUnionUF((N * N) + 2); //two extra for top and bottom virtual sites

            // initially blocking all the grid sites
            openArray = new boolean[N * N];
            for (int i = 0; i < N * N; i++) {
                openArray[i] = false;
            }
            numOfOpenSites = 0;

            // top and bottom are assigned to the last two elements of the grid array
            topIdx = N * N;
            bottomIdx = N * N + 1;

        }

    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(topIdx) == grid.find(bottomIdx);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkForException(row, col);

        int gridIdx = getGridIndex(row, col);
        return grid.find(gridIdx) == grid.find(topIdx);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkForException(row, col);

        int idx = getGridIndex(row, col);
        return openArray[idx];
    }

    // performs the union function of the newly opened site to its already opened neighbours
    private void joinWithPossibleNeighbours(int row, int col) {
        int gridIdx = getGridIndex(row, col);

        if (isInRange(row, col - 1) && isOpen(row, col - 1)) {
            // left neighbour exists
            grid.union(gridIdx, getGridIndex(row, col - 1));
        }

        if (isInRange(row, col + 1) && isOpen(row, col + 1)) {
            // right neighbour exists
            grid.union(gridIdx, getGridIndex(row, col + 1));
        }

        if (isInRange(row - 1, col) && isOpen(row - 1, col)) {
            // top neighbour exists
            grid.union(gridIdx, getGridIndex(row - 1, col));
        }

        if (isInRange(row + 1, col) && isOpen(row + 1, col)) {
            // bottom neighbour exists
            grid.union(gridIdx, getGridIndex(row + 1, col));
        }

        if (row == 1) {
            //top row...so join with topIdx
            grid.union(gridIdx, topIdx);
        } else if (row == N) {
            //bottom row...so join with bottomIdx
            grid.union(gridIdx, bottomIdx);
        }

    }

    // this function opens the site (row,col) if not open already
    public void open(int row, int col) {
        checkForException(row, col);

        if (!isOpen(row, col)) {
            openArray[getGridIndex(row, col)] = true;
            numOfOpenSites++;
            joinWithPossibleNeighbours(row, col);
        }

    }

    // this function returns the index in the grid 1D array given the row and column
    private int getGridIndex(int row, int col) {
        int i = row - 1;
        int j = col - 1;
        return (N * i) + j;
    }

    //checks if the given value of row and column is in range
    private boolean isInRange(int row, int col) {
        return !(row < 1 || row > N || col < 1 || col > N);
    }

    //throws exception if row and col not in range
    private void checkForException(int row, int col) {
        if (!isInRange(row, col)) {
            throw new IllegalArgumentException("The row and column are not within the range!");
        }
    }

}
