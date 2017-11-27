package ultimatettt.model.data;

import ultimatettt.model.Clear;

/**
 * Class to hold data for a game turn
 */
public class TurnData {

    private final CellData cell;
    private final Clear clear;
    private final int largeRow;
    private final int largeCol;
    private final int smallRow;
    private final int smallCol;
    private final boolean isOpen;

    public TurnData(CellData cell, Clear clear,
                    int largeRow, int largeCol,
                    int smallRow, int smallCol,
                    boolean isOpen) {
        this.cell = cell;
        this.clear = clear;
        this.largeRow = largeRow;
        this.largeCol = largeCol;
        this.smallRow = smallRow;
        this.smallCol = smallCol;
        this.isOpen = isOpen;
    }

    /**
     * @return the cell involved
     */
    public CellData getCell() {
        return cell;
    }

    /**
     * @return the clear status for this turn
     */
    public Clear getClear() {
        return clear;
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

    /**
     * @return true if and only if the destination grid isn't cleared
     */
    public boolean isNextOpen() {
        return isOpen;
    }

}
