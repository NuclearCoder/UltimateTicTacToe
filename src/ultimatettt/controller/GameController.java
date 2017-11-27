package ultimatettt.controller;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.events.view.CellHoveredEvent;
import ultimatettt.events.view.ViewMouseListener;
import ultimatettt.model.Clear;
import ultimatettt.model.GameData;
import ultimatettt.model.data.CellData;
import ultimatettt.model.data.GridData;
import ultimatettt.model.data.TurnData;
import ultimatettt.view.GameDisplay;

import java.awt.*;

/**
 * Class implementing actual game logic
 */
class GameController implements ViewMouseListener {

    private final GameData data;
    private final GameDisplay display;

    GameController(GameData data, GameDisplay display) {
        super();

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

            setAndCheck(largeRow, largeCol, smallRow, smallCol, data.getTurn());
        }
    }

    private void setAndCheck(int largeRow, int largeCol, int smallRow, int smallCol, Clear clear) {
        GridData global = data.getGlobal();
        GridData grid = data.getGrid(largeRow, largeCol);
        CellData cell = grid.getCell(smallRow, smallCol);

        // set single clear
        cell.setClear(clear);

        // set cell clear
        Clear largeClear = WinChecker.checkForWin(grid);
        grid.setClear(largeClear);

        // set global clear
        Clear globalClear = WinChecker.checkForWin(global);
        global.setClear(globalClear);

        //  check for a draw:

        // if that move cleared large grid and all cells are cleared without a win
        // or it didn't clear but the cell is full
        if ((largeClear != Clear.EMPTY && global.getUnclearedCells() == 0 && globalClear == Clear.EMPTY)
                || (global.getUnclearedCells() == 1 && grid.getUnclearedCells() == 0)) {
            global.setClear(Clear.DRAW);
        }

        TurnData turn = new TurnData(
                cell, clear,
                largeRow, largeCol,
                smallRow, smallCol,
                // if game is still going and the destination isn't cleared
                !isWon() && !isLargeCleared(smallRow, smallCol)
        );

        data.nextTurn(turn);
    }

    private boolean isPlacedRight(int row, int col) {
        TurnData lastPlayed = data.getLastTurn();
        return lastPlayed == null || !lastPlayed.isNextOpen() ||
                (lastPlayed.getSmallRow() == row
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

        int row = event.getLargeRow();
        int col = event.getLargeCol();

        // if we're not selecting a cell,
        // or the game has already been won,
        // or we aren't in the correct grid,
        // or the cell has already been cleared,
        // or the grid has already been cleared
        if (cell == null
                || isWon()
                || !isPlacedRight(row, col)
                || isCleared(cell)
                || isLargeCleared(row, col)) {
            // then set default cursor
            display.setCursor(Cursor.getDefaultCursor());
        } else {
            // otherwise, set hand cursor
            display.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        data.setHovered(cell);
    }

}
