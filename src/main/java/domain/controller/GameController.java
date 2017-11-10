package domain.controller;

import domain.controller.data.DataController;
import domain.model.Game;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;
import persistence.model.GameDataModel;

/**
 * The type Game controller.
 */
public class GameController {
    private static GameController mInstance = new GameController();
    private Game currentGame;
    private DataController gameDataController = GameDataModel.getInstance();

    private GameController() {
        // Empty Constructor
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static GameController getInstance() {
        return mInstance;
    }

    /**
     * Start new game.
     *
     * @param userName     the user name
     * @param computerName the computer name
     * @param role         the role
     * @param pegs         the pegs
     * @param colors       the colors
     * @param turns        the turns
     */
    public void startNewGame(String userName, String computerName, String role, int pegs, int colors, int turns) {
        Player.Role userRole = Player.Role.valueOf(role);

        Player.Role computerRole = Player.oppositeRole(userRole);
        ComputerPlayer computer = ComputerPlayer.newComputerByName(computerName, computerRole);

        currentGame = new Game(new UserPlayer(userName, userRole), computer,
                new Game.GameInfo(userName, userRole, pegs, colors, turns));
        try {
            currentGame.startGame();
        } catch (FinishGameException e) {
            currentGame = null;
        }
    }

    /**
     * Start new game.
     *
     * @param computerName the computer name
     * @param pegs         the pegs
     * @param colors       the colors
     * @param turns        the turns
     */
    public void startNewGame(String computerName, int pegs, int colors, int turns) {
        ComputerPlayer computer = ComputerPlayer.newComputerByName(computerName, Player.Role.BREAKER);

        currentGame = new Game(new DummyComputer(Player.Role.MAKER), computer,
                new Game.GameInfo(computerName, Player.Role.BREAKER, pegs, colors, turns));
        try {
            currentGame.startGame();
        } catch (FinishGameException e) {
            currentGame = null;
        }
    }

    /**
     * Gets game status.
     *
     * @return the game status
     */
    public String getGameStatus() {
        return currentGame.getGameStatus();
    }

    /**
     * Save current game.
     */
    public void saveCurrentGame() throws FinishGameException {
        if (currentGame != null) {
            gameDataController.insert(currentGame.getGameTitle(), currentGame);
            stopCurrentGame();
        }
    }

    /**
     * Stop current game.
     */
    public void stopCurrentGame() throws FinishGameException {
        if (currentGame != null) {
            currentGame.finishGame();
        }
        throw new FinishGameException();
    }
}
