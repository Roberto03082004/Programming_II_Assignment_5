package pa5;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    public static final String OUTPUT_PATH = System.getProperty("user.dir") + "/output/";
    public static char[][] solution;

    public static void main(String[] args) {
        System.out.println("Command-line arguments: " + Arrays.toString(args));
        int n = Integer.parseInt(args[0]);
        solution = new char[n][n];

        // Check if n is a power of 2
        if ((n & (n - 1)) != 0 || n <= 0) {
            System.out.println("Error: n must be a power of 2.");
            System.exit(1);
        }

        int rowIndex = Integer.parseInt(args[1]);
        int colIndex = Integer.parseInt(args[2]);

        // Check if row and column indices are valid
        if (rowIndex < 0 || rowIndex >= n || colIndex < 0 || colIndex >= n) {
            System.out.println("Error: Invalid row or column index.");
            System.exit(1);
        }

        System.out.printf("Inputs are n = %d, missing tile is at (%d, %d)%n", n, rowIndex, colIndex);

        solution = new char[n][n];
        solver(n, rowIndex, colIndex, 0, 0);

        solution[rowIndex][colIndex] = '*'; // Mark the missing tile

        printSolution(OUTPUT_PATH + "out.txt", n);
    }

    private static void solver(int dim, int hole_row, int hole_col, int start_row, int start_col) {
        if (dim == 2) {
            // Base case: solve the 2x2 sub-board
            if (hole_row == start_row && hole_col == start_col) {
                // Case 1
                solution[start_row + 1][start_col + 1] = '+';
                solution[start_row + 1][start_col] = '-';
                solution[start_row][start_col + 1] = '|';
            } else if (hole_row == start_row && hole_col == start_col + 1) {
                // Case 2
                solution[start_row + 1][start_col] = '+';
                solution[start_row + 1][start_col + 1] = '-';
                solution[start_row][start_col] = '|';
            } else if (hole_row == start_row + 1 && hole_col == start_col) {
                // Case 3
                solution[start_row][start_col + 1] = '+';
                solution[start_row][start_col] = '-';
                solution[start_row + 1][start_col + 1] = '|';
            } else {
                // Case 4
                solution[start_row][start_col] = '+';
                solution[start_row][start_col + 1] = '-';
                solution[start_row + 1][start_col] = '|';
            }
        } else {
            for (int i = start_row; i < start_row + dim; i++) {
                for (int j = start_col; j < start_col + dim; j++) {
                    solution[i][j] = ' ';
                }
            }
            // Recursive cases
            int mid = dim / 2;

            // Place the middle tile
            solution[start_row + mid - 1][start_col + mid - 1] = '+';
            solution[start_row + mid - 1][start_col + mid] = '-';
            solution[start_row + mid][start_col + mid - 1] = '|';

            if (hole_row < start_row + mid && hole_col < start_col + mid) {
                // Missing tile in the top-left quadrant (Q1)
                solver(mid, hole_row, hole_col, start_row, start_col);
            } else if (hole_row < start_row + mid && hole_col >= start_col + mid) {
                // Missing tile in the top-right quadrant (Q2)
                solver(mid, hole_row, hole_col, start_row, start_col + mid);
            } else if (hole_row >= start_row + mid && hole_col < start_col + mid) {
                // Missing tile in the bottom-left quadrant (Q3)
                solver(mid, hole_row, hole_col, start_row + mid, start_col);
            } else {
                // Missing tile in the bottom-right quadrant (Q4)
                solver(mid, hole_row, hole_col, start_row + mid, start_col + mid);
            }
        }
    }

    private static void printSolution(String fileName, int n) {
        try (PrintWriter p = new PrintWriter(fileName)) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (col != n - 1) {
                        System.out.print(solution[row][col] + " ");
                        p.print(solution[row][col] + " ");
                    } else {
                        System.out.println(solution[row][col]);
                        p.println(solution[row][col]);
                    }
                }
            }
        } catch (FileNotFoundException exp) {
            System.out.println("Fatal error: illegal output file name!");
            System.exit(1);
        }
    }


}
