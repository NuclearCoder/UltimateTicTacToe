package ultimatettt.model;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.view.GameDisplay;

import java.awt.*;

public final class GameData {

    public static final int SIZE = 3;

    public static final int EMPTY = 0;
    public static final int FIRST = -1;
    public static final int SECOND = 1;

    private final LargeGrid global;
    private final LargeGrid[][] largeGrids;

    private Cell hovered;
    private CellClickedEvent lastPlayed;

    private int turn;

    public GameData() {
        this.global = new LargeGrid();
        this.largeGrids = new LargeGrid[SIZE][SIZE];
        this.hovered = null;
        this.lastPlayed = null;
        this.turn = FIRST;
        initCells();
    }

    public LargeGrid getLargeGrid(int row, int col) {
        return largeGrids[row][col];
    }

    public Cell getCell(int largeRow, int largeCol, int row, int col) {
        return largeGrids[largeRow][largeCol].grid[row][col];
    }

    public LargeGrid getGlobal() {
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
        global.grid = largeGrids;
        global.clear = GameData.EMPTY;

        int y = GameDisplay.DISPLAY_MARGIN;
        for (int row = 0; row < SIZE; row++) {
            int x = GameDisplay.DISPLAY_MARGIN;
            for (int col = 0; col < SIZE; col++) {
                LargeGrid cell = this.new LargeGrid();
                cell.clear = GameData.EMPTY;
                cell.color = GameDisplay.COLOR_EMPTY;
                cell.rect = new Rectangle(
                        x - 1 - GameDisplay.WIN_BORDER / 2,
                        y - 1 - GameDisplay.WIN_BORDER / 2,
                        GameDisplay.LARGE_CELL_SIZE + 2 + GameDisplay.WIN_BORDER,
                        GameDisplay.LARGE_CELL_SIZE + 2 + GameDisplay.WIN_BORDER
                );
                cell.grid = initLargeGrid(x, y);

                largeGrids[row][col] = cell;

                x += GameDisplay.LARGE_CELL_SIZE + GameDisplay.LARGE_BORDER;
            }
            y += GameDisplay.LARGE_CELL_SIZE + GameDisplay.LARGE_BORDER;
        }
    }

    private Cell[][] initLargeGrid(int origX, int y) {
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

    public class Cell {
        int clear;
        Color color;
        Rectangle rect;

        public int getClear() {
            return clear;
        }

        public void setClear(int clear) {
            this.clear = clear;
            this.color = GameDisplay.getColorFor(clear);
        }

        public Color getColor() {
            return color;
        }

        public int getX() {
            return rect.x;
        }

        public int getY() {
            return rect.y;
        }

        public int getWidth () {
            return rect.width;
        }

        public int getHeight() {
            return rect.height;
        }

        public boolean contains(int x, int y) {
            return rect.contains(x, y);
        }
    }

    public class LargeGrid extends Cell {
        private Cell[][] grid;

        public Cell getCell(int row, int col) {
            return grid[row][col];
        }
    }

}
