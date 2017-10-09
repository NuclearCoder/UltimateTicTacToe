package ultimatettt.model;

public class GridData extends CellData {
    CellData[][] grid;

    public CellData getCell(int row, int col) {
        return grid[row][col];
    }
}
