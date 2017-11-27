package ultimatettt.events.view;

import ultimatettt.model.data.CellData;

/**
 * Event when a cell is clicked
 */
public class CellClickedEvent extends GenericCellEvent {

    public CellClickedEvent(CellData cell, int largeRow, int largeCol, int smallRow, int smallCol) {
        super(cell, largeRow, largeCol, smallRow, smallCol);
    }

}
