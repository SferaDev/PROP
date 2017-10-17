package domain.controller;

import data.GameData;
import data.StatData;
import data.UserData;
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
        return UserData.getInstance();
    }

    public DataController getGameController() {
        return GameData.getInstance();
    }

    public DataController getStatsController() {
        return StatData.getInstance();
    }

    public InputOutput getGameInterface() {
        return mGameInterface;
    }

    public void setGameInterface(InputOutput gameInterface) {
        mGameInterface = gameInterface;
    }
}
