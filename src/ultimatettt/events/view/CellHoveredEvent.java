package ultimatettt.events.view;

import ultimatettt.model.GameData;

public interface CellHoveredEvent {

    GameData.Cell getCell();
    int getLargeRow();
    int getLargeCol();
    int getSmallRow();
    int getSmallCol();

}
