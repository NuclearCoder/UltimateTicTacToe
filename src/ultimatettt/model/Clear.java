package ultimatettt.model;

import java.awt.*;

public enum Clear {

    FIRST(-1, new Color(59, 215, 255)),
    EMPTY(0, new Color(0, 0, 0, 0)),
    SECOND(1, new Color(255, 94, 87)),

    DRAW(2, new Color(0, 0, 0));

    public final int value;
    public final Color color;

    Clear(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public static Clear valueOf(int value) {
        //  -1  ->  [0]  ->  FIRST
        //   0  ->  [1]  ->  EMPTY
        //   1  ->  [2]  ->  SECOND
        return Clear.values()[value + 1];
    }

}
