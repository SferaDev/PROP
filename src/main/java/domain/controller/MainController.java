package domain.controller;

import data.UserControllerJSON;
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
        return UserControllerJSON.getInstance();
    }

    public InputOutput getGameInterface() {
        return new TerminalIO();
    }
}
