package ultimatettt.main;

import ultimatettt.events.game.SmallWinEvent;
import ultimatettt.events.view.CellClickedEvent;
import ultimatettt.events.view.CellHoveredEvent;
import ultimatettt.events.view.ViewMouseListener;
import ultimatettt.model.Game;
import ultimatettt.model.GameData;
import ultimatettt.model.GameModel;
import ultimatettt.view.GameDisplay;
import ultimatettt.view.MouseHandler;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        GameData data = new GameData();

        GameModel model = new Game(data);
        GameDisplay display = new GameDisplay(data);

        GameLogic logic = new GameLogic(data, model, display);

        MouseHandler mouseHandler = new MouseHandler(data);

        JFrame frame = new JFrame("Ultimate Tic Tac Toe");
        frame.setContentPane(display);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        model.addWinListener(display);

        display.addMouseListener(mouseHandler);
        display.addMouseMotionListener(mouseHandler);

        mouseHandler.addListener(logic);

        frame.setVisible(true);
    }

}
