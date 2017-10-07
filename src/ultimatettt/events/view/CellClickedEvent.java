package ultimatettt.events.view;

import ultimatettt.model.GameData;

public interface CellClickedEvent {

    GameData.Cell getCell();
    int getLargeRow();
    int getLargeCol();
    int getSmallRow();
    int getSmallCol();

}
