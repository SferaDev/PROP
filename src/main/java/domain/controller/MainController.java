package domain.controller;

import data.GameDataModel;
import data.StatDataModel;
import data.UserDataModel;
import domain.controller.data.DataController;
import domain.controller.data.UserController;
import domain.model.InputOutput;

public class MainController {
    public static final boolean DEBUG = true;

    private static MainController mInstance = new MainController();
    private static InputOutput mGameInterface;

    private MainController() {
        // Should never be instantiated
    }

    public static MainController getInstance() {
        return mInstance;
    }

    public UserController getUserController() {
        return UserDataModel.getInstance();
    }

    public DataController getGameController() {
        return GameDataModel.getInstance();
    }

    public DataController getStatsController() {
        return StatDataModel.getInstance();
    }

    public InputOutput getGameInterface() {
        return mGameInterface;
    }

    public void setGameInterface(InputOutput gameInterface) {
        mGameInterface = gameInterface;
    }
}
