package domain.controller;

import domain.InputOutput;
import domain.controller.data.DataController;
import domain.controller.data.UserDataController;
import domain.model.Game;
import domain.model.User;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;
import persistence.model.GameDataModel;
import persistence.model.UserDataModel;

public class DomainController {
    private static DomainController mInstance = new DomainController();
    private boolean mDebug = false;
    private static InputOutput mGameInterface;

    private DomainController() {
        // Empty Constructor
    }

    public static DomainController getInstance() {
        return mInstance;
    }

    public InputOutput getGameInterface() {
        return mGameInterface;
    }

    public void setGameInterface(InputOutput gameInterface) {
        mGameInterface = gameInterface;
    }

    public boolean isDebugBuild() {
        return mDebug;
    }

    public void setDebugBuild(boolean debug) {
        mDebug = debug;
    }

    public GameController getGameController() {
        return GameController.getInstance();
    }

    public StatController getStatController() {
        return StatController.getInstance();
    }

    public UserController getUserController() {
        return UserController.getInstance();
    }
}
