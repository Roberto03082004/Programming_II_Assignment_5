package pa5;

public class Board {

    // data fields
    private int size; // size of board (n)
    private Cell[][] board; // 2D array to represent board

    // constructor
    public Board(int size) {
        // initialize data fields
        this.size = size;
        board = new Cell[size][size];
        // initialize board with empty cells
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new Cell();
            }
        }
    }

    // sets missing tile
    public void setMissingCell(int row, int col) {
        // set missing cell at given row and column
        // mark missing tile with *
        board[row][col].setMark('*');
    }

    // method to solve the problem recursively
    public void solve() {
        // call helper method to solve the board
        solve(0, 0, size);
    }

    // helper method to solve the tiling problem recursively
    private void solve(int startRow, int startCol, int n) {
        // BASE CASE
        if (n < 2) {
            // tiles are L shaped so it is not possible to cover any
            // cells when size of board is less then 2
            // if n < 2 for eg. 1 then nxn = 1x1 is a single cell
            // so problem is already solved or could not be solved!
            return;
        }
        // RECURSIVE STEP
        // we can divide board in 4 quadrant
        //   -----
        //   |1|2|
        //   -----
        //   |3|4|
        //   -----
        // first find a cell that is covered with tile
        // in any quadrant
        int covered = findCoveredCellQuadrant(startRow, startCol, n);
        // now place the tile in middle such that it covers a cell in all quadrant
        // except the already covered one
        placeTile(startRow, startCol, n, covered);
        n = n / 2; // reduce size by half to make 4 quadrants
        // now recursively solve all 4 quadrants
        // for quadrant 1
        solve(startRow, startCol, n);
        // for quadrant 2
        solve(startRow, startCol + n, n);
        // for quadrant 3
        solve(startRow + n, startCol, n);
        // for quadrant 4
        solve(startRow + n, startCol + n, n);
    }

    // helper method that place a L shaped tile in middle of given region
    // such that it covers a cell in all 3 quadrant of region that does not
    // have a covered cell
    private void placeTile(int startRow, int startCol, int n, int covered) {
        // place the tile in middle such that it covers a cell in all quadrant
        // except the already covered one
        // place + for cell part that is middle in tile
        // place | for upper of lower cell
        // place - for left or right cell
        if (covered == 1) {
            // place + in top left cell of forth quadrant
            int row = startRow + n / 2;
            int col = startCol + n / 2;
            board[row][col].setMark('+');
            // place | in bottom left cell of second quadrant
            row = startRow + n / 2 - 1;
            board[row][col].setMark('|');
            // place - in top right cell of third quadrant
            row = startRow + n / 2;
            col = startCol + n / 2 - 1;
            board[row][col].setMark('-');
        } else if (covered == 2) {
            // place + in top right cell of third quadrant
            int row = startRow + n / 2;
            int col = startCol + n / 2 - 1;
            board[row][col].setMark('+');
            // place | in bottom right cell of first quadrant
            row = startRow + n / 2 - 1;
            board[row][col].setMark('|');
            // place - in top left cell of forth quadrant
            row = startRow + n / 2;
            col = startCol + n / 2;
            board[row][col].setMark('-');
        } else if (covered == 3) {
            // place + in bottom left cell of second quadrant
            int row = startRow + n / 2 - 1;
            int col = startCol + n / 2;
            board[row][col].setMark('+');
            // place | in top left cell of fourth quadrant
            row = startRow + n / 2;
            board[row][col].setMark('|');
            // place - in bottom right cell of first quadrant
            row = startRow + n / 2 - 1;
            col = startCol + n / 2 - 1;
            board[row][col].setMark('-');
        } else {
            // place + in bottom right cell of first quadrant
            int row = startRow + n / 2 - 1;
            int col = startCol + n / 2 - 1;
            board[row][col].setMark('+');
            // place | in top right cell of third quadrant
            row = startRow + n / 2;
            board[row][col].setMark('|');
            // place - in bottom left cell of second quadrant
            row = startRow + n / 2 - 1;
            col = startCol + n / 2;
            board[row][col].setMark('-');
        }
    }

    // helper method to find quadrant with a covered cell
    private int findCoveredCellQuadrant(int startRow, int startCol, int n) {
        // first divide given board in to 4 quadrant and
        // if any cell is covered in that quadrant then return that quadrant number
        n = n / 2; // reduce size by half to make 4 quadrants
        // 1 for upper left
        if (hasAnyCoveredCell(startRow, startCol, n)) {
            return 1;
        }
        // 2 for upper right
        if (hasAnyCoveredCell(startRow, startCol + n, n)) {
            return 2;
        }
        // 3 for lower left
        if (hasAnyCoveredCell(startRow + n, startCol, n)) {
            return 3;
        }
        // 4 for lower right
        if (hasAnyCoveredCell(startRow + n, startCol + n, n)) {
            return 4;
        }
        return 0;
    }

    // helper method to check if any cell is covered in given square region
    private boolean hasAnyCoveredCell(int startRow, int startCol, int n) {
        // create a loop to check for covered cell
        for (int row = startRow; row < (startRow + n); row++) {
            for (int col = startCol; col < (startCol + n); col++) {
                if (board[row][col].getMark() != ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    // string representation of board
    @Override
    public String toString() {
        // create a string object to build and return board as string
        String str = "";
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // add each cell of board
                str = str + board[row][col];
            }
            // add new line at each end of row
            str = str + "\n";
        }
        return str;
    }

}
