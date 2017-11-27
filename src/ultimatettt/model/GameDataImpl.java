package ultimatettt.model;

import java.awt.*;

import static ultimatettt.GameConstants.*;

final class GameDataImpl implements GameData {

    private final GridData global;
    private final GridData[][] grids;

    private CellData hovered;

    private Clear turn;
    private TurnData lastTurn;

    GameDataImpl() {
        this.grids = new GridData[SIZE][SIZE];
        this.global = new GridData(null, grids);
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
    }

    private void initCells() {
        int y = DISPLAY_MARGIN;
        for (int row = 0; row < SIZE; row++) {
            int x = DISPLAY_MARGIN;
            for (int col = 0; col < SIZE; col++) {
                grids[row][col] = new GridData(new Rectangle(
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

    private CellData[][] initGrid(int origX, int y) {
        CellData[][] grid = new CellData[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            int x = origX;
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = new CellData(new Rectangle(
                        x - 1, y - 1,
                        SMALL_CELL_SIZE + 1, SMALL_CELL_SIZE + 1
                ));

                x += SMALL_CELL_SIZE + SMALL_BORDER;
            }
            y += SMALL_CELL_SIZE + SMALL_BORDER;
        }

        return grid;
    }

}
