package ultimatettt.model;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.view.GameDisplay;

import java.awt.*;

public final class GameData {

    public static final int SIZE = 3;

    private final GridData global;
    private final GridData[][] grids;

    private CellData hovered;
    private CellClickedEvent lastPlayed;

    private Clear turn;

    public GameData() {
        this.global = new GridData();
        this.grids = new GridData[SIZE][SIZE];
        this.hovered = null;
        this.lastPlayed = null;
        this.turn = Clear.FIRST;
        initCells();
    }

    public GridData getGrid(int row, int col) {
        return grids[row][col];
    }

    public GridData getGlobal() {
        return global;
    }

    public CellData getHovered() {
        return hovered;
    }

    public void setHovered(CellData cell) {
        this.hovered = cell;
    }

    public CellClickedEvent getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(CellClickedEvent lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public Clear getTurn() {
        return turn;
    }

    public void nextTurn() {
        this.turn = (turn == Clear.FIRST ? Clear.SECOND : Clear.FIRST);
    }

    private void initCells() {
        global.grid = grids;
        global.clear = Clear.EMPTY;

        int y = GameDisplay.DISPLAY_MARGIN;
        for (int row = 0; row < SIZE; row++) {
            int x = GameDisplay.DISPLAY_MARGIN;
            for (int col = 0; col < SIZE; col++) {
                GridData cell = new GridData();
                cell.clear = Clear.EMPTY;
                cell.rect = new Rectangle(
                        x - 1 - GameDisplay.WIN_BORDER / 2,
                        y - 1 - GameDisplay.WIN_BORDER / 2,
                        GameDisplay.LARGE_CELL_SIZE + 2 + GameDisplay.WIN_BORDER,
                        GameDisplay.LARGE_CELL_SIZE + 2 + GameDisplay.WIN_BORDER
                );
                cell.grid = initGrid(x, y);

                grids[row][col] = cell;

                x += GameDisplay.LARGE_CELL_SIZE + GameDisplay.LARGE_BORDER;
            }
            y += GameDisplay.LARGE_CELL_SIZE + GameDisplay.LARGE_BORDER;
        }
    }

    private CellData[][] initGrid(int origX, int y) {
        CellData[][] grid = new CellData[SIZE][SIZE];
        int x;

        for (int row = 0; row < SIZE; row++) {
            x = origX;
            for (int col = 0; col < SIZE; col++) {
                CellData cell = new CellData();
                cell.clear = Clear.EMPTY;
                cell.rect = new Rectangle(
                        x - 1, y - 1,
                        GameDisplay.SMALL_CELL_SIZE + 1, GameDisplay.SMALL_CELL_SIZE + 1
                );

                grid[row][col] = cell;

                x += GameDisplay.SMALL_CELL_SIZE + GameDisplay.SMALL_BORDER;
            }
            y += GameDisplay.SMALL_CELL_SIZE + GameDisplay.SMALL_BORDER;
        }

        return grid;
    }

}
