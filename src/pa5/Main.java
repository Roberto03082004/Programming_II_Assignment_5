package pa5;


import java.util.Scanner;

public class Main {

    // main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // get size of board from first argument

            //System.out.print("Enter the size of the board (must be a power of 2): ");
            //int n = scanner.nextInt();
            int n = Integer.parseInt(args[0]);

            // check for power of 2
            // to check if n is power of 2 create a loop and check each value
            // as power of 2 till find the n
            int i = 0;
            while(Math.pow(2, i) < n) {
                i++;
            }
            if(Math.pow(2, i) != n) {
                // print error message and terminate
                System.out.println("Size of the board must be a power of 2.");
                return;
            }

            // get row and column position for missing cell
            //System.out.print("Enter the row index of the missing cell: ");
            //int row = scanner.nextInt();

            //System.out.print("Enter the column index of the missing cell: ");
            //int col = scanner.nextInt();
            int row = Integer.parseInt(args[1]);
            int col = Integer.parseInt(args[2]);

            // check for valid position
            if(row<0 || row>=n) {
                // print error message and terminate
                System.out.println("Row index is out of Bounds");
                return;
            }
            if(col<0 || col>=n) {
                // print error message and terminate
                System.out.println("Column index is out of Bounds");
                return;
            }

            // create the board object
            Board board = new Board(8);
            // set missing cell
            board.setMissingCell(row, col);
            // solve the puzzle recursively
            board.solve();
            // print the board
            System.out.println(board);

        }
        catch (NumberFormatException e) {
            // print error message and terminate
            System.out.println("Arguments must be Integer values");
        }
    }
}


