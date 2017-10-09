package ultimatettt.model;

import ultimatettt.view.GameDisplay;

import java.awt.*;

public class Cell {
    int clear;
    Color color;
    Rectangle rect;

    public int getClear() {
        return clear;
    }

    public void setClear(int clear) {
        this.clear = clear;
        this.color = GameDisplay.getColorFor(clear);
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return rect.x;
    }

    public int getY() {
        return rect.y;
    }

    public int getWidth () {
        return rect.width;
    }

    public int getHeight() {
        return rect.height;
    }

    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }
}
