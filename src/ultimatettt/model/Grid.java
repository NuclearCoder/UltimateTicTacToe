package ultimatettt.model;

public class Grid extends Cell {
    Cell[][] grid;

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }
}
