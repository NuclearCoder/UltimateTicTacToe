package ultimatettt.model;

import ultimatettt.events.game.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

abstract class AbstractGameData implements GameData {

    private final List<GameListener> listeners;

    AbstractGameData() {
        this.listeners = new CopyOnWriteArrayList<>();
    }

    public void addListener(GameListener listener) {
        listeners.add(listener);
    }

    public void removeListener(GameListener listener) {
        listeners.remove(listener);
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    void fireTurnEvent(GameTurnEvent event) {
        for (GameListener listener : listeners) {
            listener.onTurn(event);
        }
    }

    void fireGlobalWinEvent(GlobalWinEvent event) {
        for (GameListener listener : listeners) {
            listener.onGlobalWin(event);
        }
    }

    void fireGridWinEvent(GridWinEvent event) {
        for (GameListener listener : listeners) {
            listener.onGridWin(event);
        }
    }

    void fireHoverEvent(CellHoverEvent event) {
        for (GameListener listener : listeners) {
            listener.onHover(event);
        }
    }

}
