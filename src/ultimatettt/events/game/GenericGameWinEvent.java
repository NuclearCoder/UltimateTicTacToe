package ultimatettt.events.game;

import ultimatettt.events.Event;
import ultimatettt.model.Clear;

/**
 * Base event for game win
 */
public abstract class GenericGameWinEvent implements Event {

    private final Clear clear;

    public GenericGameWinEvent(Clear clear) {
        this.clear = clear;
    }

    /**
     * @return the clear status for this event
     */
    public Clear getClear() {
        return clear;
    }

}
