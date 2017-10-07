package ultimatettt.events.game;

public class SmallWinEvent implements GameEvent {

    private final int clear;
    private final int row;
    private final int col;

    public SmallWinEvent(int clear, int row, int col) {
        this.clear = clear;
        this.row = row;
        this.col = col;
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
