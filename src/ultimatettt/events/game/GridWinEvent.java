package ultimatettt.events.game;

import ultimatettt.model.Clear;

/**
 * Event when a grid is won
 */
public class GridWinEvent extends GenericGameWinEvent {

    private final int largeRow;
    private final int largeCol;

    public GridWinEvent(Clear clear, int largeRow, int largeCol) {
        super(clear);
        this.largeRow = largeRow;
        this.largeCol = largeCol;
    }

    /**
     * @return the super-row for this grid
     */
    public int getLargeRow() {
        return largeRow;
    }

    /**
     * @return the super-column for this grid
     */
    public int getLargeCol() {
        return largeCol;
    }

}
