package ultimatettt.model;

import java.awt.*;

/**
 * Class to hold data for a simple game cell (sub-cell)
 */
public class CellData {

    private Clear clear;
    private Rectangle rect;

    /**
     * Constructs a <code>CellData</code> instance.
     *
     * @param rect the boundaries of this cell
     */
    CellData(Rectangle rect) {
        this.clear = Clear.EMPTY;
        this.rect = rect;
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
     * @return true iff the point (x;y) is in the cell
     */
    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

}
