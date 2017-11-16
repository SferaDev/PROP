package domain.controller;

import domain.controller.data.DataController;
import domain.model.Game;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;
import persistence.model.GameDataModel;

import java.util.ArrayList;

/**
 * The type Game controller
 *
 * @author Alexis Rico Carreto
 */
public class GameController {
    private static final GameController mInstance = new GameController();
    private final DataController gameDataController = GameDataModel.getInstance();
    private Game currentGame;

    private GameController() {
        // Empty Constructor
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static GameController getInstance() {
        return mInstance;
    }

    /**
     * Start a new game whith an user player and a computer
     *
     * @param userName     the user name
     * @param computerName the computer name
     * @param role         the role
     * @param pegs         the number of pegs accepted in a combination
     * @param colors       the quantity of possible different colors in a combination
     * @param turns        the maxim number of turns than can be played
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
     * Start a new game computer vs computer
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
     * Save current game
     *
     * @throws FinishGameException the finish game exception
     */
    public void saveCurrentGame() throws FinishGameException {
        if (currentGame != null) {
            currentGame.prepareSave();
            gameDataController.insert(currentGame.getGameTitle(), currentGame);
            stopCurrentGame();
        }
    }

    /**
     * Stop current game
     *
     * @throws FinishGameException the finish game exception
     */
    public void stopCurrentGame() throws FinishGameException {
        if (currentGame != null) {
            currentGame.finishGame();
        }
        throw new FinishGameException();
    }

    /**
     * Gets all games of an user
     *
     * @param userName the user name
     * @return all games of an user
     */
    public ArrayList<String> getAllGames(String userName) {
        ArrayList games = gameDataController.allKeys();
        ArrayList valuesToRemove = new ArrayList();
        for (Object game : games) {
            String gameTitle = (String) game;
            if (!gameTitle.split("-")[0].equals(userName)) {
                valuesToRemove.add(game);
            }
        }
        games.removeAll(valuesToRemove);
        return games;
    }


    /**
     * Continues the game
     *
     * @param game the game to continue
     */
    public void continueGame(String game) {
        if (!gameDataController.exists(game)) return;
        currentGame = (Game) gameDataController.get(game);
        gameDataController.remove(game);

        try {
            currentGame.restoreSavedGame();
            currentGame.startGame();
        } catch (FinishGameException e) {
            currentGame = null;
        }
    }

    /**
     * Provides help
     */
    public void provideHelp() {
        if (currentGame == null) return;
        currentGame.provideHelp();
    }
}
