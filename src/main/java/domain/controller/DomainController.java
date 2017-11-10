package domain.controller;

import domain.InputOutput;

public class DomainController {
    private static DomainController mInstance = new DomainController();
    private static InputOutput mGameInterface;
    private boolean mDebug = false;

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
