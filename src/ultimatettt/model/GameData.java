package ultimatettt.model;

import ultimatettt.events.view.CellClickedEvent;

/**
 * Model interface to hold game data.
 */
public interface GameData {

    /**
     * Size of the grid (and sub-grids)
     */
    int SIZE = 3;

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
     * @return the state of last played move
     */
    CellClickedEvent getLastPlayed();

    /**
     * Sets the state of last played move
     *
     * @param lastPlayed new played move
     */
    void setLastPlayed(CellClickedEvent lastPlayed);

    /**
     * @return the player for which it's the turn
     */
    Clear getTurn();

    /**
     * Flips turn to the other player
     */
    void nextTurn();

}
