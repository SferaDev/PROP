package domain.controller;

import data.UserControllerJSON;
import domain.model.InputOutput;

public class MainController {
    private static MainController mInstance = new MainController();
    private static InputOutput mGameInterface;

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

    public void setGameInterface(InputOutput gameInterface) {
        mGameInterface = gameInterface;
    }
}
