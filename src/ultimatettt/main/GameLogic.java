package ultimatettt.main;

import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.events.view.CellHoveredEvent;
import ultimatettt.events.view.ViewMouseListener;
import ultimatettt.model.GameData;
import ultimatettt.model.GameModel;
import ultimatettt.view.GameDisplay;

import java.awt.*;

public class GameLogic implements ViewMouseListener {

    private final GameData data;
    private final GameModel model;
    private final GameDisplay display;

    public GameLogic(GameData data, GameModel model, GameDisplay display) {
        this.data = data;
        this.model = model;
        this.display = display;
    }

    @Override
    public void onCellClicked(CellClickedEvent event) {
        int largeRow = event.getLargeRow();
        int largeCol = event.getLargeCol();

        if (isPlacedRight(event)
                && !isLargeCleared(largeRow, largeCol)
                && !isCleared(event.getCell())) {

            int smallRow = event.getSmallRow();
            int smallCol = event.getSmallCol();

            model.set(largeRow, largeCol, smallRow, smallCol, data.getTurn());

            CellClickedEvent nextLastPlayed;
            if (isLargeCleared(smallRow, smallCol)) {
                // if they get sent to a cleared cell,
                // then they get to choose next turn
                nextLastPlayed = null;
            } else {
                nextLastPlayed = event;
            }

            data.setLastPlayed(nextLastPlayed);
            data.nextTurn();

            display.repaint();
        }
    }

    private boolean isPlacedRight(CellClickedEvent current) {
        CellClickedEvent lastPlayed = data.getLastPlayed();
        return lastPlayed == null
                || (lastPlayed.getSmallRow() == current.getLargeRow()
                    && lastPlayed.getSmallCol() == current.getLargeCol());
    }

    private boolean isCleared(GameData.Cell cell) {
        return cell.getClear() != GameData.EMPTY;
    }

    private boolean isLargeCleared(int largeRow, int largeCol) {
        return isCleared(data.getLargeGrid(largeRow, largeCol));
    }

    @Override
    public void onCellHovered(CellHoveredEvent event) {
        GameData.Cell cell = event.getCell();
        data.setHovered(cell);

        display.setCursor(Cursor.getPredefinedCursor(
                (cell != null && cell.getClear() == GameData.EMPTY)
                        ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));

        display.repaint();
    }

}
