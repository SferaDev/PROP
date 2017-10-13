package domain.controller;

import data.UserControllerJSON;
import domain.model.io.InputOutput;
import domain.model.io.TerminalIO;

public class MainController {
    private static MainController mInstance = new MainController();
    private static InputOutput mGameInterface = new TerminalIO();

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
        return mGameInterface;
    }
}
