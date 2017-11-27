package ultimatettt.events.game;

import ultimatettt.model.Clear;

/**
 * Event when the game is won
 */
public class GlobalWinEvent extends GenericGameWinEvent {

    public GlobalWinEvent(Clear clear) {
        super(clear);
    }

}
