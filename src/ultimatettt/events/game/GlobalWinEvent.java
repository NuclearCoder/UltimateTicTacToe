package ultimatettt.events.game;

import ultimatettt.model.Clear;

/**
 * Event for a game win
 */
public class GlobalWinEvent extends GenericGameWinEvent {

    public GlobalWinEvent(Clear clear) {
        super(clear);
    }

}
