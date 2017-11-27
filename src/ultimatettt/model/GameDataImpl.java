package ultimatettt.model;

import ultimatettt.events.game.CellHoverEvent;
import ultimatettt.events.game.GameTurnEvent;
import ultimatettt.events.game.GlobalWinEvent;
import ultimatettt.events.game.GridWinEvent;
import ultimatettt.model.data.CellData;
import ultimatettt.model.data.GridData;
import ultimatettt.model.data.TurnData;

import java.awt.*;

import static ultimatettt.GameConstants.*;

final class GameDataImpl extends AbstractGameData {

    private final Global global;
    private final Grid[][] grids;

    private CellData hovered;

    private Clear turn;
    private TurnData lastTurn;

    GameDataImpl() {
        this.grids = new Grid[SIZE][SIZE];
        this.global = new Global(grids);
        this.hovered = null;
        this.turn = Clear.FIRST;
        this.lastTurn = null;
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
        fireHoverEvent(new CellHoverEvent(cell));
    }

    @Override
    public Clear getTurn() {
        return turn;
    }

    @Override
    public TurnData getLastTurn() {
        return lastTurn;
    }

    @Override
    public void nextTurn(TurnData turnData) {
        this.turn = (turn == Clear.FIRST ? Clear.SECOND : Clear.FIRST);
        this.lastTurn = turnData;
        fireTurnEvent(new GameTurnEvent(turnData));
    }

    private void initCells() {
        int y = DISPLAY_MARGIN;
        for (int row = 0; row < SIZE; row++) {
            int x = DISPLAY_MARGIN;
            for (int col = 0; col < SIZE; col++) {
                grids[row][col] = new Grid(row, col, new Rectangle(
                        x - 1 - WIN_BORDER / 2,
                        y - 1 - WIN_BORDER / 2,
                        LARGE_CELL_SIZE + 2 + WIN_BORDER,
                        LARGE_CELL_SIZE + 2 + WIN_BORDER
                ), initGrid(x, y));

                x += LARGE_CELL_SIZE + LARGE_BORDER;
            }
            y += LARGE_CELL_SIZE + LARGE_BORDER;
        }
    }

    private Cell[][] initGrid(int origX, int y) {
        Cell[][] grid = new Cell[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            int x = origX;
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new Cell(row, col, new Rectangle(
                        x - 1, y - 1,
                        SMALL_CELL_SIZE + 1, SMALL_CELL_SIZE + 1
                ));

                x += SMALL_CELL_SIZE + SMALL_BORDER;
            }
            y += SMALL_CELL_SIZE + SMALL_BORDER;
        }

        return grid;
    }

    private class Global extends GridData {

        public Global(Grid[][] grids) {
            super(0, 0, null, grids);
        }

        @Override
        protected void onClear(Clear clear) {
            if (clear != Clear.EMPTY) {
                fireGlobalWinEvent(new GlobalWinEvent(clear));
            }
        }

    }

    private class Grid extends GridData {

        public Grid(int row, int col, Rectangle rect, Cell[][] grid) {
            super(row, col, rect, grid);
        }

        @Override
        protected void onClear(Clear clear) {
            if (clear != Clear.EMPTY) {
                fireGridWinEvent(new GridWinEvent(clear, row, col));
            }
        }

    }

    private class Cell extends CellData {

        public Cell(int row, int col, Rectangle rect) {
            super(row, col, rect);
        }

        @Override
        protected void onClear(Clear clear) {
        }

    }

}
