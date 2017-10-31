package domain.controller;

import data.GameDataModel;
import data.UserDataModel;
import domain.controller.data.DataController;
import domain.controller.data.UserDataController;
import domain.model.Game;
import domain.model.User;
import domain.model.player.Player;
import domain.model.player.UserPlayer;

public class MainController {
    public static final boolean DEBUG = true;

    private static MainController mInstance = new MainController();
    private static InputOutput mGameInterface;

    private DataController gameController = GameDataModel.getInstance();
    private UserDataController userController = UserDataModel.getInstance();

    private Game currentGame;

    private MainController() {
        // Should never be instantiated
    }

    public static MainController getInstance() {
        return mInstance;
    }

    public InputOutput getGameInterface() {
        return mGameInterface;
    }

    public void setGameInterface(InputOutput gameInterface) {
        mGameInterface = gameInterface;
    }

    public boolean createUser(String username, String password) {
        // TODO: Use exceptions!!
        if (userController.exists(username)) return false;
        userController.insert(new User(username, password));
        return true;
    }

    public boolean loginUser(String userName, String password) {
        // TODO: Use exceptions!!
        return userController.login(userName, password);
    }

    public boolean existsUser(String userName) {
        return userController.exists(userName);
    }

    public void startNewGame(String userName, String role, int pegs, int colors, int turns) {
        currentGame = new Game(new UserPlayer(userName, Player.Role.valueOf(role)),
                new Game.GameInfo(pegs, colors, turns));
        currentGame.startGame();
    }

    public void saveCurrentGame() {
        gameController.insert(currentGame);
    }
}
