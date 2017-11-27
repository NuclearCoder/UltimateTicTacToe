package ultimatettt.events.game;

/**
 * Listener for the game events
 */
public interface GameListener {

    /**
     * Responds to a game turn
     *
     * @param event the corresponding event object
     */
    void onTurn(GameTurnEvent event);

    /**
     * Responds to a global win
     *
     * @param event the corresponding event object
     */
    void onGlobalWin(GlobalWinEvent event);

    /**
     * Responds to a grid win
     *
     * @param event the corresponding event object
     */
    void onGridWin(GridWinEvent event);

}
