package ultimatettt.events.view;

import ultimatettt.model.GameData;

// this "backwards" inheritance is to have contravariant classes,
// which is not supported as a language feature in Java.
public class GenericCellMouseEvent implements CellClickedEvent, CellHoveredEvent {

    private final GameData.Cell cell;
    private final int largeRow;
    private final int largeCol;
    private final int smallRow;
    private final int smallCol;

    public GenericCellMouseEvent(GameData.Cell cell,
                                 int largeRow, int largeCol,
                                 int smallRow, int smallCol) {
        this.cell = cell;
        this.largeRow = largeRow;
        this.largeCol = largeCol;
        this.smallRow = smallRow;
        this.smallCol = smallCol;
    }

    @Override
    public GameData.Cell getCell() {
        return cell;
    }

    @Override
    public int getLargeRow() {
        return largeRow;
    }

    @Override
    public int getLargeCol() {
        return largeCol;
    }

    @Override
    public int getSmallRow() {
        return smallRow;
    }

    @Override
    public int getSmallCol() {
        return smallCol;
    }


}
