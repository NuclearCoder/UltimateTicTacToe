package ultimatettt.model;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.view.GameDisplay;

import java.awt.*;

final class GameDataImpl implements GameData {

    private final GridData global;
    private final GridData[][] grids;

    private CellData hovered;
    private CellClickedEvent lastPlayed;

    private Clear turn;

    public GameDataImpl() {
        this.grids = new GridData[SIZE][SIZE];
        this.global = new GridData(null, grids);
        this.hovered = null;
        this.lastPlayed = null;
        this.turn = Clear.FIRST;
        initCells();
    }

    @Override
    public GridData getGrid(int row, int col) {
        return grids[row][col];
    }

    @Override
    public GridData getGlobal() {
        return global;
    }

    @Override
    public CellData getHovered() {
        return hovered;
    }

    @Override
    public void setHovered(CellData cell) {
        this.hovered = cell;
    }

    @Override
    public CellClickedEvent getLastPlayed() {
        return lastPlayed;
    }

    @Override
    public void setLastPlayed(CellClickedEvent lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    @Override
    public Clear getTurn() {
        return turn;
    }

    @Override
    public void nextTurn() {
        this.turn = (turn == Clear.FIRST ? Clear.SECOND : Clear.FIRST);
    }

    private void initCells() {
        int y = GameDisplay.DISPLAY_MARGIN;
        for (int row = 0; row < SIZE; row++) {
            int x = GameDisplay.DISPLAY_MARGIN;
            for (int col = 0; col < SIZE; col++) {
                grids[row][col] = new GridData(new Rectangle(
                        x - 1 - GameDisplay.WIN_BORDER / 2,
                        y - 1 - GameDisplay.WIN_BORDER / 2,
                        GameDisplay.LARGE_CELL_SIZE + 2 + GameDisplay.WIN_BORDER,
                        GameDisplay.LARGE_CELL_SIZE + 2 + GameDisplay.WIN_BORDER
                ), initGrid(x, y));

                x += GameDisplay.LARGE_CELL_SIZE + GameDisplay.LARGE_BORDER;
            }
            y += GameDisplay.LARGE_CELL_SIZE + GameDisplay.LARGE_BORDER;
        }
    }

    private CellData[][] initGrid(int origX, int y) {
        CellData[][] grid = new CellData[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            int x = origX;
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new CellData(new Rectangle(
                        x - 1, y - 1,
                        GameDisplay.SMALL_CELL_SIZE + 1, GameDisplay.SMALL_CELL_SIZE + 1
                ));

                x += GameDisplay.SMALL_CELL_SIZE + GameDisplay.SMALL_BORDER;
            }
            y += GameDisplay.SMALL_CELL_SIZE + GameDisplay.SMALL_BORDER;
        }

        return grid;
    }

}
