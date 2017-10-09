package ultimatettt.model;

import static ultimatettt.model.GameData.SIZE;

public class GridData extends CellData {
    CellData[][] grid;

    public CellData getCell(int row, int col) {
        return grid[row][col];
    }

    public int getUnclearedCells() {
        int sum = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col].clear != Clear.EMPTY) {
                    sum++;
                }
            }
        }

        return sum;
    }
}
