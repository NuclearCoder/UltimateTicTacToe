package ultimatettt.view;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.events.view.CellHoveredEvent;
import ultimatettt.events.view.GenericCellMouseEvent;
import ultimatettt.events.view.ViewMouseListener;
import ultimatettt.model.GameData;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static ultimatettt.model.GameData.SIZE;
import static ultimatettt.view.GameDisplay.*;

public class MouseHandler implements MouseListener, MouseMotionListener {

    public static final CellHoveredEvent DEFAULT_HOVER_EVENT = new GenericCellMouseEvent(null, 0, 0, 0, 0);

    private final GameData data;
    private final List<ViewMouseListener> listeners;

    public MouseHandler(GameData data) {
        this.data = data;
        this.listeners = new CopyOnWriteArrayList<>();
    }

    private void handleClick(int x, int y) {
        CellClickedEvent cell = getCellAtOrNull(x, y);
        if (cell == null) return;
        dispatchEvent(cell);
    }

    private void handleMove(int x, int y) {
        CellHoveredEvent cell = getCellAtOrNull(x, y);
        dispatchEvent(cell != null ? cell : DEFAULT_HOVER_EVENT);
    }

    private GenericCellMouseEvent getCellAtOrNull(int x, int y) {
        int largeRow = clamp( (y - LARGE_BORDER) / (LARGE_CELL_SIZE + LARGE_BORDER), 0, SIZE - 1);
        int largeCol = clamp((x - LARGE_BORDER) / (LARGE_CELL_SIZE + LARGE_BORDER), 0, SIZE - 1);
        GameData.LargeGrid grid = data.getLargeGrid(largeRow, largeCol);

        if (grid.contains(x, y)) {
            int localX = (x - LARGE_BORDER) % (LARGE_CELL_SIZE + LARGE_BORDER);
            int localY = (y - LARGE_BORDER) % (LARGE_CELL_SIZE + LARGE_BORDER);

            int smallRow = clamp(localY / (SMALL_CELL_SIZE + SMALL_BORDER), 0, GameData.SIZE - 1);
            int smallCol = clamp(localX / (SMALL_CELL_SIZE + SMALL_BORDER), 0, GameData.SIZE - 1);

            GameData.Cell cell = grid.getCell(smallRow, smallCol);

            if (cell.contains(x, y)) {
                return new GenericCellMouseEvent(cell, largeRow, largeCol, smallRow, smallCol);
            }
        }

        return null;
    }

    public void addListener(ViewMouseListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ViewMouseListener listener) {
        listeners.remove(listener);
    }

    public void removeListeners() {
        listeners.clear();
    }

    private void dispatchEvent(CellClickedEvent event) {
        for (ViewMouseListener listener : listeners) {
            listener.onCellClicked(event);
        }
    }

    private void dispatchEvent(CellHoveredEvent event) {
        for (ViewMouseListener listener : listeners) {
            listener.onCellHovered(event);
        }
    }

    private static int clamp(int x, int min, int max) {
        return Math.max(min, Math.min(max, x));
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

}
