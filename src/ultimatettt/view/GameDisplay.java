package ultimatettt.view;

import ultimatettt.events.game.*;
import ultimatettt.model.GameData;
import ultimatettt.model.data.CellData;
import ultimatettt.model.data.GridData;
import ultimatettt.model.data.TurnData;

import javax.swing.*;
import java.awt.*;

import static ultimatettt.GameConstants.*;

/**
 * Game display Swing component
 */
public class GameDisplay extends JComponent implements GameListener {

    private final GameData data;

    public GameDisplay(GameData data) {
        this.data = data;

        setDoubleBuffered(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }

    @Override
    public void paint(Graphics g) {
        draw((Graphics2D) g);
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
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
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

        TurnData lastPlayed = data.getLastTurn();
        if (lastPlayed != null
                && lastPlayed.isNextOpen()
                && lastPlayed.getSmallRow() == largeRow
                && lastPlayed.getSmallCol() == largeCol) {
            Stroke stroke = g.getStroke();
            g.setStroke(new BasicStroke(2f));
            g.setColor(Color.BLACK);
            g.drawRect(rect.x, rect.y, rect.width, rect.height);
            g.setStroke(stroke);
        }
    }

    @Override
    public void onTurn(GameTurnEvent event) {
        repaint();
    }

    @Override
    public void onGlobalWin(GlobalWinEvent event) {
        repaint();
    }

    @Override
    public void onGridWin(GridWinEvent event) {
        repaint();
    }

    @Override
    public void onHover(CellHoverEvent event) {
        repaint();
    }

}
