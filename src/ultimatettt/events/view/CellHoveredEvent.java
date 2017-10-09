package ultimatettt.events.view;

import ultimatettt.model.CellData;

public class CellHoveredEvent extends GenericCellMouseEvent {

    public CellHoveredEvent(CellData cell, int largeRow, int largeCol, int smallRow, int smallCol) {
        super(cell, largeRow, largeCol, smallRow, smallCol);
    }

}
