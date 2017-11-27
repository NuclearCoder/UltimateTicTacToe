package ultimatettt.events.game;

import ultimatettt.model.data.TurnData;

/**
 * Event for a turn
 */
public class GameTurnEvent {

    private final TurnData data;

    public GameTurnEvent(TurnData data) {
        this.data = data;
    }

    /**
     * @return the corresponding turn data
     */
    public TurnData getData() {
        return data;
    }

}
