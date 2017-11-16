package domain.controller;

import domain.model.Receiver;

/**
 * The type Domain controller
 *
 * @author Alexis Rico Carreto
 */
public class DomainController {
    private static final DomainController mInstance = new DomainController();
    private static Receiver mGameInterface;
    private boolean mDebug = false;

    private DomainController() {
        // Empty Constructor
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static DomainController getInstance() {
        return mInstance;
    }

    /**
     * Gets game interface
     *
     * @return the game interface
     */
    public Receiver getGameInterface() {
        return mGameInterface;
    }

    /**
     * Sets game interface
     *
     * @param gameInterface the game interface
     */
    public void setGameInterface(Receiver gameInterface) {
        mGameInterface = gameInterface;
    }

    /**
     * Checks if is a debug build
     *
     * @return the boolean
     */
    public boolean isDebugBuild() {
        return mDebug;
    }

    /**
     * Sets debug build
     *
     * @param debug the debug
     */
    public void setDebugBuild(boolean debug) {
        mDebug = debug;
    }

    /**
     * Gets game controller
     *
     * @return the game controller
     */
    public GameController getGameController() {
        return GameController.getInstance();
    }

    /**
     * Gets stat controller
     *
     * @return the stat controller
     */
    public StatController getStatController() {
        return StatController.getInstance();
    }

    /**
     * Gets user controller
     *
     * @return the user controller
     */
    public UserController getUserController() {
        return UserController.getInstance();
    }
}
