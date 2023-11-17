# Tiling Problem Solver

This Java program solves the "tiling problem" using recursion. The tiling problem involves covering a square-shaped board with L-shaped tiles in a way that only one cell remains uncovered.

## Classes

### Main

The `Main` class is the entry point for the program. It reads command-line arguments, creates a `Board` object, sets the missing cell, solves the puzzle, and prints the resulting board.

#### Methods

- `main(String[] args)`: The main method reads command-line arguments, validates input, and initiates the tiling problem solution.

### Board

The `Board` class represents the game board and contains methods to set up the board, set the missing cell, and solve the tiling problem recursively.

#### Instance Fields

- `size`: The size of the board (n).
- `board`: A 2D array of `Cell` objects representing the game board.

#### Constructors

- `Board(int size)`: Initializes the board with empty cells.

#### Methods

- `setMissingCell(int row, int col)`: Sets the missing cell on the board.
- `solve()`: Initiates the recursive solution to the tiling problem.
- `solve(int startRow, int startCol, int n)`: Recursive helper method to solve the tiling problem.
- `placeTile(int startRow, int startCol, int n, int covered)`: Places an L-shaped tile on the board.
- `findCoveredCellQuadrant(int startRow, int startCol, int n)`: Finds the quadrant with a covered cell.
- `hasAnyCoveredCell(int startRow, int startCol, int n)`: Checks if any cell is covered in a given square region.
- `toString()`: Overrides the `toString` method to provide a string representation of the board.

### Cell

The `Cell` class represents an individual cell on the board.

#### Instance Fields

- `mark`: The character representing the mark on the cell (' ', '+', '-', '|', '*').

#### Constructors

- `Cell()`: Initializes the cell with an empty mark.

#### Methods

- `setMark(char c)`: Sets the mark on the cell.
- `getMark()`: Retrieves the mark on the cell.
- `toString()`: Overrides the `toString` method to provide a string representation of the cell.

## Usage

To run the program in IntelliJ IDEA:

1. Open the project in IntelliJ IDEA.
2. Navigate to the `Main` class.
3. Right-click on the `Main` class.
4. Select "Edit 'Main.main()'".
5. In the "Program arguments" section, enter the desired command-line arguments (e.g., `8 3 1` for board size, row index, and column index).
6. Click "OK" to save the configuration.

Run the `Main` class. The program will use the specified command-line arguments.

## Author

- Roberto Callejas - [rcall037@fiu.edu](rcall037@fiu.edu) - [GitHub](https://github.com/Roberto03082004)
