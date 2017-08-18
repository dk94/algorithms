import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF instance;
    private boolean[] grid;
    private int N;
    private int numberOfOpenSites = 0;
    private boolean isPercolate = false;

    public Percolation(int n) {
        instance = new WeightedQuickUnionUF(n * n + 3);
        grid = new boolean[n * n];
        N = n;

    }

    private int map2Dto1D(int row, int col) {
        return row * N - N + col - 1;
    }

    private boolean isValid(int index) {
        if (index < 0)
            throw new IllegalArgumentException();
        else
            return true;
    }

    public void open(int row, int col) {
        int compiledInteger = map2Dto1D(row, col);

        if (isValid(compiledInteger) && !isOpen(row, col)) {
            grid[compiledInteger] = true;
            if ((compiledInteger) < N) {
                instance.union(compiledInteger, N*N);
            }
          
            if (compiledInteger + 1 < N * N && grid[compiledInteger + 1] == true) {
                instance.union(compiledInteger, compiledInteger + 1);
            }

            if (compiledInteger - 1 > 0 && grid[compiledInteger - 1] == true) {
                instance.union(compiledInteger, compiledInteger - 1);
            }

            if (compiledInteger + N < N * N && grid[compiledInteger + N] == true) {
                instance.union(compiledInteger, compiledInteger + N);
            }

            if (compiledInteger - N > 0 && grid[compiledInteger - N] == true) {
                instance.union(compiledInteger, compiledInteger - N);
            }

            if(instance.connected(compiledInteger, N*N)&&(compiledInteger) >= N*N-N &&( compiledInteger) < N*N){
                isPercolate=true;
                instance.union(compiledInteger, N*N+2);
            }

            numberOfOpenSites++;
        }

    } // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        int compiledInteger = map2Dto1D(row, col);
        if (isValid(compiledInteger)) {
            return (grid[compiledInteger] == true) ? true : false;
        } else
            return false;

    } // is site (row, col) open?

    public boolean isFull(int row, int col) {
        int compiledInteger = map2Dto1D(row, col);
        if (isValid(compiledInteger)) {
            if(percolates()){
               return instance.connected(N * N + 2, compiledInteger);
            }
            else{
               return instance.connected(N * N, compiledInteger);
            }
        } else
            return false;

    } // is site (row, col) full?

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    } // number of open sites

    public boolean percolates() {
        return isPercolate;
    } // does the system percolate?

    public static void main(String[] args) {
       

    } // test client (optional)
}
