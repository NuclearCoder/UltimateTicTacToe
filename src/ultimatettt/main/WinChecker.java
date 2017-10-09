package ultimatettt.main;

import ultimatettt.model.Clear;
import ultimatettt.model.GridData;

import static ultimatettt.model.GameData.SIZE;

@SuppressWarnings("Duplicates")
class WinChecker {

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
                sum += grid.getCell(row, col).getClear().value;
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
                sum += grid.getCell(row, col).getClear().value;
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
            sum += grid.getCell(k, k).getClear().value;
            sum2 += grid.getCell((SIZE - 1) - k, k).getClear().value;
        }

        sum /= SIZE;
        sum2 /= SIZE;

        if (Math.abs(sum) > Math.abs(sum2)) return sum;
        else return sum2;
    }

}