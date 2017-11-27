package ultimatettt.model.data;

import ultimatettt.model.Clear;

import java.awt.*;

import static ultimatettt.GameConstants.SIZE;

/**
 * Class to hold data for a large game grid (super-cell)
 */
public abstract class GridData extends CellData {

    protected final CellData[][] grid;

    /**
     * Constructs a <code>GridData</code> instance.
     *
     * @param row the row of this grid
     * @param col the column of this grid
     * @param rect the boundaries of this grid
     * @param grid the sub-grid this super-cell is composed of
     */
    protected GridData(int row, int col, Rectangle rect, CellData[][] grid) {
        super(row, col, rect);
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

    /**
     * @return the number of cells that aren't cleared in this grid
     */
    public int getUnclearedCells() {
        int sum = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col].getClear() == Clear.EMPTY) {
                    sum++;
                }
            }
        }

        return sum;
    }

}
