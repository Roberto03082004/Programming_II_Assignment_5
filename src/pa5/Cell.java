package pa5;

public class Cell {

    // data fields
    private char mark;

    // constructor
    public Cell() {
        // initialize cell with no mark (empty space)
        mark = ' ';
    }

    // setter
    public void setMark(char c) {
        mark = c;
    }

    // getter
    public char getMark() {
        return mark;
    }

    // string representation of cell
    @Override
    public String toString() {
        return "" + mark;
    }

}
