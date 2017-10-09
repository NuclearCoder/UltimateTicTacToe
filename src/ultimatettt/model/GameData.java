package ultimatettt.model;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.view.GameDisplay;

import java.awt.*;

public final class GameData {

    public static final int SIZE = 3;

    public static final int EMPTY = 0;
    public static final int FIRST = -1;
    public static final int SECOND = 1;

    private final Grid global;
    private final Grid[][] grids;

    private Cell hovered;
    private CellClickedEvent lastPlayed;

    private int turn;

    public GameData() {
        this.global = new Grid();
        this.grids = new Grid[SIZE][SIZE];
        this.hovered = null;
        this.lastPlayed = null;
        this.turn = FIRST;
        initCells();
    }

    public Grid getGrid(int row, int col) {
        return grids[row][col];
    }

    public Cell getCell(int largeRow, int largeCol, int row, int col) {
        return grids[largeRow][largeCol].grid[row][col];
    }

    public Grid getGlobal() {
        return global;
    }

    public Cell getHovered() {
        return hovered;
    }

    public CellClickedEvent getLastPlayed() {
        return lastPlayed;
    }

    public int getTurn() {
        return turn;
    }

    public void setHovered(Cell cell) {
        this.hovered = cell;
    }

    public void setLastPlayed(CellClickedEvent lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public void nextTurn() {
        this.turn = (turn == FIRST ? SECOND : FIRST);
    }

    private void initCells() {
        global.grid = grids;
        global.clear = GameData.EMPTY;

        int y = GameDisplay.DISPLAY_MARGIN;
        for (int row = 0; row < SIZE; row++) {
            int x = GameDisplay.DISPLAY_MARGIN;
            for (int col = 0; col < SIZE; col++) {
                Grid cell = new Grid();
                cell.clear = GameData.EMPTY;
                cell.color = GameDisplay.COLOR_EMPTY;
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

    private Cell[][] initGrid(int origX, int y) {
        Cell[][] grid = new Cell[SIZE][SIZE];
        int x;

        for (int row = 0; row < SIZE; row++) {
            x = origX;
            for (int col = 0; col < SIZE; col++) {
                Cell cell = new Cell();
                cell.clear = GameData.EMPTY;
                cell.color = GameDisplay.COLOR_EMPTY;
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
