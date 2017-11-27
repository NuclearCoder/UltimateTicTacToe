package ultimatettt;

import ultimatettt.events.view.CellHoveredEvent;

import java.awt.*;

/**
 * Global constant pool class
 */
public class GameConstants {

    public static final int SIZE = 3;

    /* display constants */

    public static final Color COLOR_HOVER = new Color(0, 0, 0, 50);

    public static final int SMALL_BORDER = 4;
    public static final int SMALL_CELL_SIZE = 32;

    public static final int LARGE_BORDER = 8;
    public static final int LARGE_CELL_SIZE = SIZE * (SMALL_CELL_SIZE + SMALL_BORDER) - SMALL_BORDER;

    public static final int WIN_BORDER = LARGE_BORDER / 2;
    public static final int DISPLAY_MARGIN = 8;
    public static final AlphaComposite WIN_FILTER_COMPOSITE = AlphaComposite.SrcOver.derive(0.5f);
    public static final CellHoveredEvent DEFAULT_HOVER_EVENT = new CellHoveredEvent(null, 0, 0, 0, 0);
    private static final int BOARD_SIZE = SIZE * (LARGE_CELL_SIZE + LARGE_BORDER) - LARGE_BORDER;
    private static final int DISPLAY_SIZE = BOARD_SIZE + 2 * DISPLAY_MARGIN;
    public static final Dimension PREFERRED_SIZE = new Dimension(DISPLAY_SIZE, DISPLAY_SIZE);

}
