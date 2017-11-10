package domain.controller;

import domain.controller.data.DataController;
import domain.model.Game;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;
import persistence.model.GameDataModel;

public class GameController {
    private static GameController mInstance = new GameController();

    private GameController() {
        // Empty Constructor
    }

    public static GameController getInstance() {
        return mInstance;
    }

    private Game currentGame;

    private DataController gameDataController = GameDataModel.getInstance();

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

    public String getGameStatus() {
        return currentGame.getGameStatus();
    }
}
