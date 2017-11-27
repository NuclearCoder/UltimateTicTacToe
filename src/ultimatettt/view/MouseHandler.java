package ultimatettt.view;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.events.view.CellHoveredEvent;
import ultimatettt.events.view.ViewMouseListener;
import ultimatettt.model.CellData;
import ultimatettt.model.GameData;
import ultimatettt.model.GridData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static ultimatettt.GameConstants.*;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private final GameData data;
    private final List<ViewMouseListener> listeners;

    public MouseHandler(GameData data) {
        this.data = data;
        this.listeners = new CopyOnWriteArrayList<>();
    }

    /*
    ensures x stays in [0..max]
     */
    private static int coerceUp(int x, int max) {
        return Math.max(0, Math.min(max, x));
    }

    private void handleClick(int x, int y) {
        EventData d = getCellDataOrNull(x, y);
        if (d == null) return;
        fireClickedEvent(new CellClickedEvent(
                d.cell, d.largeRow, d.largeCol, d.smallRow, d.smallCol
        ));
    }

    private void handleMove(int x, int y) {
        EventData d = getCellDataOrNull(x, y);
        fireHoveredEvent(d == null
                ? DEFAULT_HOVER_EVENT
                        : new CellHoveredEvent(
                        d.cell, d.largeRow, d.largeCol, d.smallRow, d.smallCol
                )
        );
    }

    public void addListener(ViewMouseListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ViewMouseListener listener) {
        listeners.remove(listener);
    }

    private EventData getCellDataOrNull(int x, int y) {
        int largeRow = coerceUp((y - LARGE_BORDER) / (LARGE_CELL_SIZE + LARGE_BORDER), SIZE - 1);
        int largeCol = coerceUp((x - LARGE_BORDER) / (LARGE_CELL_SIZE + LARGE_BORDER), SIZE - 1);
        GridData grid = data.getGrid(largeRow, largeCol);

        if (grid.contains(x, y)) {
            int localX = (x - LARGE_BORDER) % (LARGE_CELL_SIZE + LARGE_BORDER);
            int localY = (y - LARGE_BORDER) % (LARGE_CELL_SIZE + LARGE_BORDER);

            int smallRow = coerceUp(localY / (SMALL_CELL_SIZE + SMALL_BORDER), SIZE - 1);
            int smallCol = coerceUp(localX / (SMALL_CELL_SIZE + SMALL_BORDER), SIZE - 1);

            CellData cell = grid.getCell(smallRow, smallCol);

            if (cell.contains(x, y)) {
                return new EventData(cell, largeRow, largeCol, smallRow, smallCol);
            }
        }

        return null;
    }

    private void fireClickedEvent(CellClickedEvent event) {
        for (ViewMouseListener listener : listeners) {
            listener.onCellClicked(event);
        }
    }

    private void fireHoveredEvent(CellHoveredEvent event) {
        for (ViewMouseListener listener : listeners) {
            listener.onCellHovered(event);
        }
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    // mouse methods

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        handleMove(x, y);
        handleClick(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        handleMove(x, y);
        handleClick(x, y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        handleMove(x, y);
        handleClick(x, y);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        handleMove(e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        handleMove(e.getX(), e.getY());
    }

    // mouse motion methods

    @Override
    public void mouseDragged(MouseEvent e) {
        handleMove(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        handleMove(e.getX(), e.getY());
    }

    private static class EventData {
        private final CellData cell;
        private final int largeRow, largeCol;
        private final int smallRow, smallCol;

        EventData(CellData cell, int largeRow, int largeCol, int smallRow, int smallCol) {
            this.cell = cell;
            this.largeRow = largeRow;
            this.largeCol = largeCol;
            this.smallRow = smallRow;
            this.smallCol = smallCol;
        }
    }

}
