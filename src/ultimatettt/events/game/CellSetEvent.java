package ultimatettt.events.game;

import ultimatettt.events.view.ViewEvent;
import ultimatettt.model.GameData;

public class CellSetEvent implements ViewEvent {

    private final int largeRow;
    private final int largeCol;
    private final int row;
    private final int col;
    private final int clear;

    public CellSetEvent(int row, int col, int clear) {
        this.largeRow = row / GameData.LARGE_SIZE;
        this.largeCol = col / GameData.LARGE_SIZE;

        this.row = row % GameData.LARGE_SIZE;
        this.col = col % GameData.LARGE_SIZE;

        this.clear = clear;
    }

    public int getLargeRow() {
        return largeRow;
    }

    public int getLargeCol() {
        return largeCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getClear() {
        return clear;
    }

}
