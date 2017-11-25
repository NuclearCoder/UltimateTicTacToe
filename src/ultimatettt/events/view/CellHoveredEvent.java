package ultimatettt.events.view;

import ultimatettt.model.CellData;

/**
 * Event when a cell is being hovered
 */
public class CellHoveredEvent extends GenericCellEvent {

    public CellHoveredEvent(CellData cell, int largeRow, int largeCol, int smallRow, int smallCol) {
        super(cell, largeRow, largeCol, smallRow, smallCol);
    }

}
