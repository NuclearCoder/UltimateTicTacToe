package ultimatettt.main;

import ultimatettt.model.GameData;
import ultimatettt.view.GameDisplay;
import ultimatettt.view.MouseHandler;

import javax.swing.*;
import java.awt.*;

class Main {

    public static void main(String[] args) {
        GameData data = new GameData();
        GameDisplay display = new GameDisplay(data);
        GameLogic logic = new GameLogic(data, display);

        MouseHandler mouseHandler = new MouseHandler(data);
        display.addMouseListener(mouseHandler);
        display.addMouseMotionListener(mouseHandler);

        mouseHandler.addListener(logic);

        // TODO: hook up AI

        JFrame frame = new JFrame("Ultimate Tic Tac Toe");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

}
