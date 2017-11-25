package ultimatettt.events.view;

/**
 * Listener for cell mouse events
 */
public interface ViewMouseListener {

    /**
     * Responds to a cell click
     *
     * @param event the corresponding event object
     */
    void onCellClicked(CellClickedEvent event);

    /**
     * Responds to a cell hover
     *
     * @param event the corresponding event object
     */
    void onCellHovered(CellHoveredEvent event);

}
