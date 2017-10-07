package ultimatettt.events.game;

public class WinEvent implements GameEvent {

    private final int clear;

    public WinEvent(int clear) {
        this.clear = clear;
    }

    public int getClear() {
        return clear;
    }

}
