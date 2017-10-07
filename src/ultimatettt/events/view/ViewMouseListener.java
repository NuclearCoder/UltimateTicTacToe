package ultimatettt.events.view;

import ultimatettt.model.GameData;

public interface ViewMouseListener {

    void onCellClicked(CellClickedEvent event);
    void onCellHovered(CellHoveredEvent event);

}
