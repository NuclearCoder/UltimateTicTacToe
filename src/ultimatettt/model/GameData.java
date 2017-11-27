package ultimatettt.model;

/**
 * Model interface to hold game data.
 */
public interface GameData {

    /**
     * @param row the row in the board
     * @param col the column in the board
     * @return the data object for the (row;col) grid
     */
    GridData getGrid(int row, int col);

    /**
     * @return the grid data representing the global game board
     */
    GridData getGlobal();

    /**
     * @return the currently hovered cell, or null if none
     */
    CellData getHovered();

    /**
     * Sets the currently hovered cell, or null if none
     *
     * @param cell the new hovered cell
     */
    void setHovered(CellData cell);

    /**
     * @return the player for which it's the turn
     */
    Clear getTurn();

    /**
     * @return the previous turn data
     */
    TurnData getLastTurn();

    /**
     * Flips turn to the other player, with the turn data
     */
    void nextTurn(TurnData turnData);

}
