package ultimatettt.controller;

import ultimatettt.model.Clear;
import ultimatettt.model.data.GridData;

import static ultimatettt.GameConstants.SIZE;

/**
 * Utility class to check whether a grid has been won or not.
 */
@SuppressWarnings("Duplicates")
class WinChecker {

    /*
    The algorithm used here is summing rows, columns, and diagonals.
    Each cell has either a value of -1, 0, or 1.
    If any of the sums has magnitude SIZE, then we have a win.
    The sign of that sum determines for which side the win is.
     */

    /**
     * Checks for a win on the grid.
     *
     * @param grid the grid to check
     * @return true iff there is a win in the grid
     */
    static Clear checkForWin(GridData grid) {
        return Clear.valueOf(intCheckForWin(grid));
    }

    private static int intCheckForWin(GridData grid) {
        int row = checkForRow(grid);
        if (row != 0) return row;
        int col = checkForCol(grid);
        if (col != 0) return col;
        return checkForDia(grid);
    }

    private static int checkForRow(GridData grid) {
        for (int row = 0; row < SIZE; row++) {
            int sum = 0;
            for (int col = 0; col < SIZE; col++) {
                sum += grid.getCell(row, col).getClear().getValue();
            }

            sum /= SIZE;
            if (sum != 0) return sum;
        }

        return 0;
    }

    private static int checkForCol(GridData grid) {
        for (int col = 0; col < SIZE; col++) {
            int sum = 0;
            for (int row = 0; row < SIZE; row++) {
                sum += grid.getCell(row, col).getClear().getValue();
            }

            sum /= SIZE;
            if (sum != 0) return sum;
        }

        return 0;
    }

    private static int checkForDia(GridData grid) {
        int sum = 0;
        int sum2 = 0;

        for (int k = 0; k < SIZE; k++) {
            sum += grid.getCell(k, k).getClear().getValue();
            sum2 += grid.getCell((SIZE - 1) - k, k).getClear().getValue();
        }

        sum /= SIZE;
        sum2 /= SIZE;

        if (Math.abs(sum) > Math.abs(sum2)) return sum;
        else return sum2;
    }

}