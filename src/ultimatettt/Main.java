package ultimatettt;

import ultimatettt.controller.MainController;

import javax.swing.*;

/**
 * Main entry point for the application
 */
class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainController::new);
    }

}
