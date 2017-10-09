package ultimatettt.events.view;

import ultimatettt.model.Cell;

public class CellHoveredEvent extends GenericCellMouseEvent {

    public CellHoveredEvent(Cell cell, int largeRow, int largeCol, int smallRow, int smallCol) {
        super(cell, largeRow, largeCol, smallRow, smallCol);
    }

}
