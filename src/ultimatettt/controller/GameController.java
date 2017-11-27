package ultimatettt.controller;

import ultimatettt.events.game.GameListener;
import ultimatettt.events.game.GameTurnEvent;
import ultimatettt.events.game.GlobalWinEvent;
import ultimatettt.events.game.GridWinEvent;
import ultimatettt.events.view.ViewMouseListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Model for game logic
 */
abstract class GameController implements ViewMouseListener {

    private final List<GameListener> listeners;

    GameController() {
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

    protected void fireTurnEvent(GameTurnEvent event) {
        for (GameListener listener : listeners) {
            listener.onTurn(event);
        }
    }

    protected void fireGlobalWinEvent(GlobalWinEvent event) {
        for (GameListener listener : listeners) {
            listener.onGlobalWin(event);
        }
    }

    protected void fireGridWinEvent(GridWinEvent event) {
        for (GameListener listener : listeners) {
            listener.onGridWin(event);
        }
    }

}
