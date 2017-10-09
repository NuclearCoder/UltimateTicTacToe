package ultimatettt.view;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.model.CellData;
import ultimatettt.model.GameData;
import ultimatettt.model.GridData;

import java.awt.*;

import static ultimatettt.model.GameData.SIZE;

public class GameDisplay extends Canvas {

    private static final Color COLOR_HOVER = new Color(0, 0, 0, 50);

    public static final int SMALL_BORDER = 4;
    public static final int SMALL_CELL_SIZE = 32;

    public static final int LARGE_BORDER = 8;
    public static final int LARGE_CELL_SIZE = GameData.SIZE * (SMALL_CELL_SIZE + SMALL_BORDER) - SMALL_BORDER;

    public static final int DISPLAY_MARGIN = 8;
    public static final int DISPLAY_SIZE = SIZE * (LARGE_CELL_SIZE + LARGE_BORDER) + DISPLAY_MARGIN;

    public static final int WIN_BORDER = LARGE_BORDER / 2;

    private static final AlphaComposite WIN_FILTER_COMPOSITE = AlphaComposite.SrcOver.derive(0.5f);

    private final GameData data;

    public GameDisplay(GameData data) {
        this.data = data;

        setSize(new Dimension(DISPLAY_SIZE, DISPLAY_SIZE));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.clearRect(0, 0, getWidth(), getHeight());

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                paintGrid(g2d, data.getGrid(row, col), row, col);
            } // col
        } // row

        CellData global = data.getGlobal();
        g2d.setComposite(WIN_FILTER_COMPOSITE);
        g2d.setColor(global.getColor());
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintGrid(Graphics2D g2d, GridData grid, int largeRow, int largeCol) {
        for (int row = 0; row < GameData.SIZE; row++) {
            for (int col = 0; col < GameData.SIZE; col++) {
                CellData cell = grid.getCell(row, col);
                Rectangle rect = cell.getBounds();

                g2d.setColor(cell.getColor());
                g2d.fillRect(rect.x, rect.y, rect.width, rect.height);

                g2d.setColor(Color.BLACK);
                g2d.drawRect(rect.x, rect.y, rect.width, rect.height);

                // use physical equality
                if (cell == data.getHovered()) {
                    g2d.setColor(COLOR_HOVER);
                    g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
                }
            }
        }

        Rectangle rect = grid.getBounds();

        g2d.setComposite(WIN_FILTER_COMPOSITE);
        g2d.setColor(grid.getColor());
        g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
        g2d.setComposite(AlphaComposite.SrcOver);

        CellClickedEvent lastPlayed = data.getLastPlayed();
        if (lastPlayed != null
                && lastPlayed.getSmallRow() == largeRow
                && lastPlayed.getSmallCol() == largeCol) {
            Stroke stroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(2f));
            g2d.setColor(Color.BLACK);
            g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
            g2d.setStroke(stroke);
        }
    }

}
