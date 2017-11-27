package ultimatettt.events.game;

import ultimatettt.model.data.CellData;

/**
 * Event when a cell is hovered (by controller)
 */
public class CellHoverEvent {

    private final CellData cell;

    public CellHoverEvent(CellData cell) {
        this.cell = cell;
    }

    /**
     * @return the cell involved with this event
     */
    public CellData getCell() {
        return cell;
    }

}
