package ultimatettt.events.view;

import ultimatettt.model.Cell;

abstract class GenericCellMouseEvent {

    private final Cell cell;
    private final int largeRow;
    private final int largeCol;
    private final int smallRow;
    private final int smallCol;

    GenericCellMouseEvent(Cell cell,
                                 int largeRow, int largeCol,
                                 int smallRow, int smallCol) {
        this.cell = cell;
        this.largeRow = largeRow;
        this.largeCol = largeCol;
        this.smallRow = smallRow;
        this.smallCol = smallCol;
    }

    public Cell getCell() {
        return cell;
    }

    public int getLargeRow() {
        return largeRow;
    }

    public int getLargeCol() {
        return largeCol;
    }

    public int getSmallRow() {
        return smallRow;
    }

    public int getSmallCol() {
        return smallCol;
    }

}
