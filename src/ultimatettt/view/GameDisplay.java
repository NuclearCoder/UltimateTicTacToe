package ultimatettt.view;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.model.CellData;
import ultimatettt.model.GameData;
import ultimatettt.model.GridData;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static ultimatettt.model.GameData.SIZE;

public class GameDisplay extends Canvas {

    private static final Color COLOR_HOVER = new Color(0, 0, 0, 50);

    public static final int SMALL_BORDER = 4;
    public static final int SMALL_CELL_SIZE = 32;

    public static final int LARGE_BORDER = 8;
    public static final int LARGE_CELL_SIZE = SIZE * (SMALL_CELL_SIZE + SMALL_BORDER) - SMALL_BORDER;

    private static final int BOARD_SIZE = SIZE * (LARGE_CELL_SIZE + LARGE_BORDER) - LARGE_BORDER;

    public static final int DISPLAY_MARGIN = 8;
    private static final int DISPLAY_SIZE = BOARD_SIZE + 2 * DISPLAY_MARGIN;

    public static final int WIN_BORDER = LARGE_BORDER / 2;

    private static final AlphaComposite WIN_FILTER_COMPOSITE = AlphaComposite.SrcOver.derive(0.5f);

    private final GameData data;
    private BufferStrategy bs;

    public GameDisplay(GameData data) {
        this.data = data;
        this.bs = null;

        setSize(new Dimension(DISPLAY_SIZE, DISPLAY_SIZE));
    }

    @Override
    public void paint(Graphics g) {
        if (bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }

        Graphics2D g2d = null;
        do {
            try {
                g2d = (Graphics2D) bs.getDrawGraphics();
                draw(g2d);
            } finally {
                if (g2d != null)
                    g2d.dispose();
            }
            bs.show();
        } while (bs.contentsLost());
    }

    private void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, getWidth(), getHeight());

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                paintGrid(g, data.getGrid(row, col), row, col);
            } // col
        } // row

        CellData global = data.getGlobal();
        g.setComposite(WIN_FILTER_COMPOSITE);
        g.setColor(global.getColor());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintGrid(Graphics2D g, GridData grid, int largeRow, int largeCol) {
        for (int row = 0; row < GameData.SIZE; row++) {
            for (int col = 0; col < GameData.SIZE; col++) {
                CellData cell = grid.getCell(row, col);
                Rectangle rect = cell.getBounds();

                g.setColor(cell.getColor());
                g.fillRect(rect.x, rect.y, rect.width, rect.height);

                g.setColor(Color.BLACK);
                g.drawRect(rect.x, rect.y, rect.width, rect.height);

                // use physical equality
                if (cell == data.getHovered()) {
                    g.setColor(COLOR_HOVER);
                    g.fillRect(rect.x, rect.y, rect.width, rect.height);
                }
            }
        }

        Rectangle rect = grid.getBounds();

        g.setComposite(WIN_FILTER_COMPOSITE);
        g.setColor(grid.getColor());
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        g.setComposite(AlphaComposite.SrcOver);

        CellClickedEvent lastPlayed = data.getLastPlayed();
        if (lastPlayed != null
                && lastPlayed.getSmallRow() == largeRow
                && lastPlayed.getSmallCol() == largeCol) {
            Stroke stroke = g.getStroke();
            g.setStroke(new BasicStroke(2f));
            g.setColor(Color.BLACK);
            g.drawRect(rect.x, rect.y, rect.width, rect.height);
            g.setStroke(stroke);
        }
    }

}
