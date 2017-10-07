package ultimatettt.model;

import ultimatettt.events.game.SmallWinEvent;
import ultimatettt.events.game.WinEvent;
import ultimatettt.events.game.WinListener;
import ultimatettt.view.MouseHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game implements GameModel {

    private final GameData data;
    private final List<WinListener> listeners;

    public Game(GameData data) {
        this.data = data;
        this.listeners = new CopyOnWriteArrayList<>();
    }

    @Override
    public void set(int largeRow, int largeCol, int smallRow, int smallCol, int clear) {
        GameData.LargeGrid grid = data.getLargeGrid(largeRow, largeCol);
        grid.getCell(smallRow, smallCol).setClear(clear);

        int smallCleared = WinChecker.checkForWin(grid);
        grid.setClear(smallCleared);
        if (smallCleared != 0) {
            dispatchEvent(new SmallWinEvent(smallCleared, largeRow, largeCol));
        }

        GameData.LargeGrid global = data.getGlobal();
        int cleared = WinChecker.checkForWin(global);
        global.setClear(cleared);
        if (cleared != 0) {
            dispatchEvent(new WinEvent(cleared));
        }
    }

    @Override
    public void addWinListener(WinListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeWinListener(WinListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void removeWinListeners() {
        listeners.clear();
    }

    private void dispatchEvent(SmallWinEvent event) {
        for (WinListener listener : listeners) {
            listener.onSmallWin(event);
        }
    }

    private void dispatchEvent(WinEvent event) {
        for (WinListener listener : listeners) {
            listener.onWin(event);
        }
    }

}
