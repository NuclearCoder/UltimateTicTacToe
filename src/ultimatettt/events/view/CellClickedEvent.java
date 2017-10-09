package ultimatettt.events.view;

import ultimatettt.model.CellData;

public class CellClickedEvent extends GenericCellMouseEvent {

    public CellClickedEvent(CellData cell, int largeRow, int largeCol, int smallRow, int smallCol) {
        super(cell, largeRow, largeCol, smallRow, smallCol);
    }

}
