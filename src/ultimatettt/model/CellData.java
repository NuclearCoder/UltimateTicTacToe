package ultimatettt.model;

import java.awt.*;

public class CellData {
    Clear clear;
    Rectangle rect;

    public Clear getClear() {
        return clear;
    }

    public void setClear(Clear clear) {
        this.clear = clear;
    }

    public Color getColor() {
        return clear.color;
    }

    public Rectangle getBounds() {
        return rect;
    }

    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }
}
