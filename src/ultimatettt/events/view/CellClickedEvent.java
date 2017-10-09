package ultimatettt.events.view;

import ultimatettt.model.Cell;

public class CellClickedEvent extends GenericCellMouseEvent {

    public CellClickedEvent(Cell cell, int largeRow, int largeCol, int smallRow, int smallCol) {
        super(cell, largeRow, largeCol, smallRow, smallCol);
    }

}
