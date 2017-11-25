package ultimatettt.model;

import java.awt.*;

/**
 * Enumeration of different clear statuses possible for a cell/grid.
 */
public enum Clear {

    /**
     * Clear status for the first player.
     */
    FIRST(-1, new Color(59, 215, 255)),

    /**
     * Clear status for an unclaimed cell.
     */
    EMPTY(0, new Color(0, 0, 0, 0)),

    /**
     * Clear status for the second player.
     */
    SECOND(1, new Color(255, 94, 87)),

    /**
     * Clear status for a drawn grid.
     */
    DRAW(2, new Color(0, 0, 0));

    private final int value;
    private final Color color;

    Clear(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    /**
     * Returns a Clear instance for a cell integer value.
     * The value must be either -1, 0, or 1,
     * matched respectively with FIRST, EMPTY, and SECOND.
     *
     * @param value the integer value
     * @return the corresponding Clear instance
     */
    public static Clear valueOf(int value) {
        if (value < -1 || value > 1) {
            throw new IllegalArgumentException("Clear integer value can only be -1, 0, or 1.");
        }
        //  -1  ->  [0]  ->  FIRST
        //   0  ->  [1]  ->  EMPTY
        //   1  ->  [2]  ->  SECOND
        return Clear.values()[value + 1];
    }

    /**
     * @return the corresponding integer value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the color for this type of cell
     */
    public Color getColor() {
        return color;
    }

}
