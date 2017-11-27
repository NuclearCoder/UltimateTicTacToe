package ultimatettt.events.view;

import ultimatettt.events.Event;
import ultimatettt.model.data.CellData;

/**
 * Base event for cell
 */
abstract class GenericCellEvent implements Event {

    private final CellData cell;
    private final int largeRow;
    private final int largeCol;
    private final int smallRow;
    private final int smallCol;

    GenericCellEvent(CellData cell,
                     int largeRow, int largeCol,
                     int smallRow, int smallCol) {
        this.cell = cell;
        this.largeRow = largeRow;
        this.largeCol = largeCol;
        this.smallRow = smallRow;
        this.smallCol = smallCol;
    }

    /**
     * @return the cell involved with this event
     */
    public CellData getCell() {
        return cell;
    }

    /**
     * @return the super-row for this cell
     */
    public int getLargeRow() {
        return largeRow;
    }

    /**
     * @return the super-column for this cell
     */
    public int getLargeCol() {
        return largeCol;
    }

    /**
     * @return the sub-row for this cell
     */
    public int getSmallRow() {
        return smallRow;
    }

    /**
     * @return the sub-column for this cell
     */
    public int getSmallCol() {
        return smallCol;
    }

}
