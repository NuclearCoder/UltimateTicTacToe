package ultimatettt.model;

import java.awt.*;

import static ultimatettt.model.GameDataImpl.SIZE;

/**
 * Class to hold data for a large game grid (super-cell)
 */
public class GridData extends CellData {

    private CellData[][] grid;

    /**
     * Constructs a <code>GridData</code> instance.
     *
     * @param rect the boundaries of this grid
     * @param grid the sub-grid this super-cell is composed of
     */
    GridData(Rectangle rect, CellData[][] grid) {
        super(rect);
        this.grid = grid;
    }

    /**
     * @param row the row in the grid
     * @param col the column in the grid
     * @return the data object for the (row;col) cell
     */
    public CellData getCell(int row, int col) {
        return grid[row][col];
    }

    public int getUnclearedCells() {
        int sum = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col].getClear() != Clear.EMPTY) {
                    sum++;
                }
            }
        }

        return sum;
    }

}
