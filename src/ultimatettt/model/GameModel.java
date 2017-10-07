package ultimatettt.model;

import ultimatettt.events.game.WinListener;

public interface GameModel {

    void set(int largeRow, int largeCol, int smallRow, int smallCol, int clear);

    void addWinListener(WinListener listener);
    void removeWinListener(WinListener listener);
    void removeWinListeners();

}
