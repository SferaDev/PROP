package domain.controller;

import data.UserControllerRealm;
import domain.model.io.InputOutput;
import domain.model.io.TerminalIO;

public class MainController {
    private static MainController mInstance = new MainController();

    public static MainController getInstance() {
        return mInstance;
    }

    private MainController() {
        // Should never be instantiated
    }

    public UserController getUserController() {
        return UserControllerRealm.getInstance();
    }

    public InputOutput getGameInterface() {
        return new TerminalIO();
    }
}
