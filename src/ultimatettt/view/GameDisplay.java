package ultimatettt.view;

import ultimatettt.events.game.SmallWinEvent;
import ultimatettt.events.game.WinEvent;
import ultimatettt.events.game.WinListener;
import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.model.GameData;

import javax.swing.*;
import java.awt.*;

import static ultimatettt.model.GameData.LARGE_SIZE;
import static ultimatettt.model.GameData.SMALL_SIZE;

public class GameDisplay extends JPanel implements WinListener {

    public static final Color COLOR_HOVER = new Color(0, 0, 0, 50);

    public static final Color COLOR_EMPTY = new Color(0, 0, 0, 0);
    public static final Color COLOR_FIRST = new Color(59, 215, 255);
    public static final Color COLOR_SECOND = new Color(255, 94, 87);

    public static final int SMALL_BORDER = 4;
    public static final int SMALL_CELL_SIZE = 32;

    public static final int LARGE_BORDER = 8;
    public static final int LARGE_CELL_SIZE = SMALL_SIZE * (SMALL_CELL_SIZE + SMALL_BORDER) - SMALL_BORDER;

    public static final int DISPLAY_MARGIN = 8;
    public static final int DISPLAY_SIZE = LARGE_SIZE * (LARGE_CELL_SIZE + LARGE_BORDER) - LARGE_BORDER + DISPLAY_MARGIN - 1 ;

    public static final int WIN_BORDER = LARGE_BORDER / 2;

    private static final AlphaComposite WIN_FILTER_COMPOSITE = AlphaComposite.SrcOver.derive(0.5f);

    private final GameData data;

    public GameDisplay(GameData data) {
        this.data = data;

        setPreferredSize(new Dimension(DISPLAY_SIZE, DISPLAY_SIZE));
    }

    @Override
    public void onWin(WinEvent event) {
        setWin(event.getClear());
    }

    @Override
    public void onSmallWin(SmallWinEvent event) {
        setSmallWin(event.getRow(), event.getCol(), event.getClear());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.clearRect(0, 0, getWidth(), getHeight());

        for (int row = 0; row < LARGE_SIZE; row++) {
            for (int col = 0; col < LARGE_SIZE; col++) {
                paintLargeGrid(g2d, data.getLargeGrid(row, col), row, col);
            } // largeCol
        } // largeRow

        GameData.Cell global = data.getGlobal();
        g2d.setComposite(WIN_FILTER_COMPOSITE);
        g2d.setColor(global.getColor());
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintLargeGrid(Graphics2D g2d, GameData.LargeGrid grid, int largeRow, int largeCol) {
        for (int row = 0; row < SMALL_SIZE; row++) {
            for (int col = 0; col < SMALL_SIZE; col++) {
                GameData.Cell cell = grid.getCell(row, col);

                g2d.setColor(cell.getColor());
                g2d.fillRect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());

                g2d.setColor(Color.BLACK);
                g2d.drawRect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());

                // use physical equality
                if (cell == data.getHovered()) {
                    g2d.setColor(COLOR_HOVER);
                    g2d.fillRect(cell.getX(), cell.getY(), cell.getWidth(), cell.getHeight());
                }
            }
        }

        g2d.setComposite(WIN_FILTER_COMPOSITE);
        g2d.setColor(grid.getColor());
        g2d.fillRect(grid.getX(), grid.getY(), grid.getWidth(), grid.getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);

        CellClickedEvent lastPlayed = data.getLastPlayed();
        if (lastPlayed != null
                && lastPlayed.getSmallRow() == largeRow
                && lastPlayed.getSmallCol() == largeCol) {
            Stroke stroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(2f));
            g2d.setColor(Color.BLACK);
            g2d.drawRect(grid.getX(), grid.getY(), grid.getWidth(), grid.getHeight());
            g2d.setStroke(stroke);
        }
    }

    private void setCell(int largeRow, int largeCol, int row, int col, int clear) {
        data.getCell(largeRow, largeCol, row, col).setClear(clear);
        repaint();
    }

    private void setSmallWin(int largeRow, int largeCol, int clear) {
        data.getLargeGrid(largeRow, largeCol).setClear(clear);
        repaint();
    }

    private void setWin(int clear) {
        data.getGlobal().setClear(clear);
        repaint();
    }

    public static Color getColorFor(int clear) {
        switch (clear) {
            case GameData.EMPTY:
                return COLOR_EMPTY;
            case GameData.FIRST:
                return COLOR_FIRST;
            case GameData.SECOND:
                return COLOR_SECOND;
            default:
                throw new IllegalArgumentException("Unexpected grid value");
        }
    }

}
