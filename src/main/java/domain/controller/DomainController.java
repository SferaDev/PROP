package domain.controller;

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
    private static InputOutput mGameInterface;
    private boolean mDebug = false;
    private DataController gameDataController = GameDataModel.getInstance();
    private UserDataController userDataController = UserDataModel.getInstance();

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
        if (userDataController.exists(username)) return false;
        userDataController.insert(username, new User(username, password));
        return true;
    }

    public boolean loginUser(String userName, String password) {
        // TODO: Use exceptions!!
        return userDataController.login(userName, password);
    }

    public boolean existsUser(String userName) {
        return userDataController.exists(userName);
    }

    public void startNewGame(String userName, String computerName, String role, int pegs, int colors, int turns) {
        Player.Role userRole = Player.Role.valueOf(role);

        Player.Role computerRole = Player.oppositeRole(userRole);
        ComputerPlayer computer = ComputerPlayer.newComputerByName(computerName, computerRole);

        currentGame = new Game(new UserPlayer(userName, userRole), computer,
                new Game.GameInfo(userName, userRole, pegs, colors, turns));
        currentGame.startGame();
    }

    public void startNewGame(String computerName, int pegs, int colors, int turns) {
        ComputerPlayer computer = ComputerPlayer.newComputerByName(computerName, Player.Role.BREAKER);

        currentGame = new Game(new DummyComputer(Player.Role.MAKER), computer,
                new Game.GameInfo(computerName, Player.Role.BREAKER, pegs, colors, turns));
        currentGame.startGame();
    }

    public void saveCurrentGame() {
        gameDataController.insert(currentGame.getGameTitle(), currentGame);
    }

    public boolean isDebugBuild() {
        return mDebug;
    }

    public void setDebugBuild(boolean debug) {
        mDebug = debug;
    }

    public String getGameStatus() {
        return currentGame.getGameStatus();
    }
}
