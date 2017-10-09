package ultimatettt.main;

import ultimatettt.model.GameData;
import ultimatettt.view.GameDisplay;
import ultimatettt.view.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        GameData data = new GameData();

        GameDisplay display = new GameDisplay(data);

        GameLogic logic = new GameLogic(data, display);

        MouseHandler mouseHandler = new MouseHandler(data);

        JFrame frame = new JFrame("Ultimate Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.CENTER);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        display.addMouseListener(mouseHandler);
        display.addMouseMotionListener(mouseHandler);

        mouseHandler.addListener(logic);

        frame.setVisible(true);
    }

}
