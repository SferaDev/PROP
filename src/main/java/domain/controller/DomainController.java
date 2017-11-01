package domain.controller;

import data.GameDataModel;
import data.UserDataModel;
import domain.controller.data.DataController;
import domain.controller.data.UserDataController;
import domain.model.Game;
import domain.model.User;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;

public class DomainController {
    public static final boolean DEBUG = true;

    private static DomainController mInstance = new DomainController();
    private static InputOutput mGameInterface;

    private DataController gameController = GameDataModel.getInstance();
    private UserDataController userController = UserDataModel.getInstance();

    private Game currentGame;

    private DomainController() {
        // Should never be instantiated
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

    public void startNewGame(String userName, String computerName, String role, int pegs, int colors, int turns) {
        Player.Role userRole = Player.Role.valueOf(role);

        Player.Role computerRole = Player.oppositeRole(userRole);
        ComputerPlayer computer = ComputerPlayer.computerByName(computerName, computerRole);

        currentGame = new Game(new UserPlayer(userName, userRole), computer,
                new Game.GameInfo(userName, userRole, pegs, colors, turns));
        currentGame.startGame();
    }

    public void startNewGame(String computerName, int pegs, int colors, int turns) {
        ComputerPlayer computer = ComputerPlayer.computerByName(computerName, Player.Role.BREAKER);

        currentGame = new Game(new DummyComputer(Player.Role.MAKER), computer,
                new Game.GameInfo(computerName, Player.Role.BREAKER, pegs, colors, turns));
        currentGame.startGame();
    }

    public void saveCurrentGame() {
        gameController.insert(currentGame);
    }
}
