package domain.controller;

import domain.InputOutput;

/**
 * The type Domain controller.
 */
public class DomainController {
    private static DomainController mInstance = new DomainController();
    private static InputOutput mGameInterface;
    private boolean mDebug = false;

    private DomainController() {
        // Empty Constructor
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DomainController getInstance() {
        return mInstance;
    }

    /**
     * Gets game interface.
     *
     * @return the game interface
     */
    public InputOutput getGameInterface() {
        return mGameInterface;
    }

    /**
     * Sets game interface.
     *
     * @param gameInterface the game interface
     */
    public void setGameInterface(InputOutput gameInterface) {
        mGameInterface = gameInterface;
    }

    /**
     * Is debug build boolean.
     *
     * @return the boolean
     */
    public boolean isDebugBuild() {
        return mDebug;
    }

    /**
     * Sets debug build.
     *
     * @param debug the debug
     */
    public void setDebugBuild(boolean debug) {
        mDebug = debug;
    }

    /**
     * Gets game controller.
     *
     * @return the game controller
     */
    public GameController getGameController() {
        return GameController.getInstance();
    }

    /**
     * Gets stat controller.
     *
     * @return the stat controller
     */
    public StatController getStatController() {
        return StatController.getInstance();
    }

    /**
     * Gets user controller.
     *
     * @return the user controller
     */
    public UserController getUserController() {
        return UserController.getInstance();
    }
}
