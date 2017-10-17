package domain.controller;

import data.model.GameData;
import data.model.StatData;
import data.model.UserData;
import domain.model.InputOutput;

public class MainController {
    private static MainController mInstance = new MainController();
    private static InputOutput mGameInterface;

    private MainController() {
        // Should never be instantiated
    }

    public static MainController getInstance() {
        return mInstance;
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
