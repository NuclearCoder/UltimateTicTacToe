package ultimatettt.model.data;

import ultimatettt.model.Clear;

import java.awt.*;

/**
 * Class to hold data for a simple game cell (sub-cell)
 */
public abstract class CellData {

    protected final int row;
    protected final int col;

    private Clear clear;
    private final Rectangle rect;

    /**
     * Constructs a <code>CellData</code> instance.
     *
     * @param row the row of this cell
     * @param col the column of this cell
     * @param rect the boundaries of this cell
     */
    protected CellData(int row, int col, Rectangle rect) {
        this.row = row;
        this.col = col;
        this.clear = Clear.EMPTY;
        this.rect = rect;
    }

    /**
     * @return the row of this cell
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column of this cell
     */
    public int getCol() {
        return col;
    }

    /**
     * @return the current clear status
     */
    public Clear getClear() {
        return clear;
    }

    /**
     * Sets the clear status.
     *
     * @param clear the new clear status
     */
    public void setClear(Clear clear) {
        this.clear = clear;
    }

    /**
     * @return the cell color, based on its clear status
     */
    public Color getColor() {
        return clear.getColor();
    }

    /**
     * @return the cell boundaries, copied to prevent modifications
     */
    public Rectangle getBounds() {
        return new Rectangle(rect);
    }

    /**
     * Shorthand method for <code>getBounds().contains(x, y)</code>
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if and only if the point (x;y) is in the cell
     */
    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    protected abstract void onClear(Clear clear);

}
