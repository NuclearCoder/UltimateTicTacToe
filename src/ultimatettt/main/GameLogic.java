package ultimatettt.main;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.events.view.CellHoveredEvent;
import ultimatettt.events.view.ViewMouseListener;
import ultimatettt.model.CellData;
import ultimatettt.model.Clear;
import ultimatettt.model.GameData;
import ultimatettt.model.GridData;
import ultimatettt.view.GameDisplay;

import java.awt.*;

class GameLogic implements ViewMouseListener {

    private final GameData data;
    private final GameDisplay display;

    GameLogic(GameData data, GameDisplay display) {
        this.data = data;
        this.display = display;
    }

    @Override
    public void onCellClicked(CellClickedEvent event) {
        // if the game is already won, no point is processing anything
        if (isWon()) return;

        int largeRow = event.getLargeRow();
        int largeCol = event.getLargeCol();
        int smallRow = event.getSmallRow();
        int smallCol = event.getSmallCol();

        if (isPlacedRight(largeRow, largeCol)
                && !isLargeCleared(largeRow, largeCol)
                && !isCleared(event.getCell())) {

            boolean doSetLP = setAndCheck(largeRow, largeCol, smallRow, smallCol, data.getTurn());
            if (doSetLP) {
                data.setLastPlayed(event);
            }

            data.nextTurn();

            display.repaint();
        }
    }

    private boolean setAndCheck(int largeRow, int largeCol, int smallRow, int smallCol, Clear clear) {
        boolean setLP = true; // whether or not to set the last played event

        GridData global = data.getGlobal();
        GridData grid = data.getGrid(largeRow, largeCol);
        CellData cell = grid.getCell(smallRow, smallCol);

        // set single clear
        cell.setClear(clear);
        // if the destination cell is cleared, un-set last played
        if (isLargeCleared(smallRow, smallCol)) {
            data.setLastPlayed(null);
            setLP = false;
        }

        // set cell clear
        Clear largeClear = WinChecker.checkForWin(grid);
        grid.setClear(largeClear);
        // if it did clear, un-set last played
        if (largeClear != Clear.EMPTY) {
            data.setLastPlayed(null);
            setLP = false;
        }

        // set global clear
        Clear globalClear = WinChecker.checkForWin(global);
        global.setClear(globalClear);
        // if it did clear, un-set last played
        if (globalClear != Clear.EMPTY) {
            data.setLastPlayed(null);
            setLP = false;
        }

        //  check for a draw:

        // if that move cleared large grid and all cells are cleared without a win
        // or it didn't clear but the cell is full
        if ((largeClear != Clear.EMPTY && global.getUnclearedCells() == 0 && globalClear == Clear.EMPTY)
                || (global.getUnclearedCells() == 1 && grid.getUnclearedCells() == 0)) {
            global.setClear(Clear.DRAW);
            data.setLastPlayed(null);
            setLP = false;
        }

        return setLP;
    }

    private boolean isPlacedRight(int row, int col) {
        CellClickedEvent lastPlayed = data.getLastPlayed();
        return lastPlayed == null
                || (lastPlayed.getSmallRow() == row
                && lastPlayed.getSmallCol() == col);
    }

    private boolean isCleared(CellData cell) {
        return cell.getClear() != Clear.EMPTY;
    }

    private boolean isLargeCleared(int largeRow, int largeCol) {
        return isCleared(data.getGrid(largeRow, largeCol));
    }

    private boolean isWon() {
        return isCleared(data.getGlobal());
    }

    @Override
    public void onCellHovered(CellHoveredEvent event) {
        CellData cell = event.getCell();
        data.setHovered(cell);

        int row = event.getLargeRow();
        int col = event.getLargeCol();

        // if we're not selecting a cell,
        // or the game has already been won,
        // or we aren't in the correct grid,
        // or the cell has already been cleared,
        if (cell == null
                || isWon()
                || !isPlacedRight(row, col)
                || isCleared(cell)) {
            // then set default cursor
            display.setCursor(Cursor.getDefaultCursor());
        } else {
            // otherwise, set hand cursor
            display.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        display.repaint();
    }

}
