package ultimatettt.main;

import ultimatettt.model.GameData;

import static ultimatettt.model.GameData.SIZE;

@SuppressWarnings("Duplicates")
public class WinChecker {

    public static int checkForWin(GameData.LargeGrid grid) {
        int row = checkForRow(grid);
        if (row != 0) return row;
        int col = checkForCol(grid);
        if (col != 0) return col;
        return checkForDia(grid);
    }

    private static int checkForRow(GameData.LargeGrid grid) {
        for (int row = 0; row < SIZE; row++) {
            int sum = 0;
            for (int col = 0; col < SIZE; col++) {
                sum += grid.getCell(row, col).getClear();
            }

            sum /= SIZE;
            if (sum != 0) return sum;
        }

        return 0;
    }

    private static int checkForCol(GameData.LargeGrid grid) {
        for (int col = 0; col < SIZE; col++) {
            int sum = 0;
            for (int row = 0; row < SIZE; row++) {
                sum += grid.getCell(row, col).getClear();
            }

            sum /= SIZE;
            if (sum != 0) return sum;
        }

        return 0;
    }

    private static int checkForDia(GameData.LargeGrid grid) {
        int sum = 0;
        int sum2 = 0;

        for (int k = 0; k < SIZE; k++) {
            sum += grid.getCell(k, k).getClear();
            sum2 += grid.getCell((SIZE - 1) - k, k).getClear();
        }

        sum /= SIZE;
        sum2 /= SIZE;

        if (Math.abs(sum) > Math.abs(sum2)) return sum;
        else return sum2;
    }

}