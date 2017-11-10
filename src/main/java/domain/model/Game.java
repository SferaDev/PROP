package domain.model;

import domain.controller.StatController;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.pow;

/**
 * The type Game.
 */
public class Game implements java.io.Serializable {
    private Status gameStatus;

    private Player gameMaker, gameBreaker;

    private GameInfo gameInfo;

    private ColorRow correctGuess;
    private ArrayList<ColorRow> mGuess = new ArrayList<>();
    private ArrayList<ControlRow> mControl = new ArrayList<>();

    private int gameTurn = 1;

    /**
     * Instantiates a new Game.
     *
     * @param user1 the user 1
     * @param user2 the user 2
     * @param info  the info
     *
     * @pre user1 and user2 roles are opposite
     *
     */
    public Game(Player user1, Player user2, GameInfo info) {
        switch (user1.getPlayerRole()) {
            case MAKER:
                gameMaker = user1;
                gameBreaker = user2;
                break;
            case BREAKER:
                gameBreaker = user1;
                gameMaker = user2;
                break;
        }

        gameInfo = info;

        gameStatus = Status.START;
    }

    /**
     * Start game.
     */
    public void startGame() throws FinishGameException {
        while (gameStatus != Status.CORRECT && gameStatus != Status.FINISHED) {
            switch (gameStatus) {
                case START:
                    correctGuess = gameMaker.makerGuess(gameInfo.mPegs, gameInfo.mColors);
                    gameStatus = Status.GUESS;
                    break;
                case GUESS:
                    ColorRow input = gameBreaker.breakerGuess(gameInfo.mPegs, gameInfo.mColors);
                    mGuess.add(input);

                    ControlRow control = gameMaker.scoreGuess(input);
                    ControlRow correctControl = ComputerPlayer.compareGuess(correctGuess, input);

                    if (!correctControl.equals(control)) {
                        gameMaker.notifyInvalidInput();
                    }

                    gameBreaker.receiveControl(correctControl);
                    mControl.add(correctControl);

                    if (input.equals(correctGuess)) {
                        gameStatus = Status.CORRECT;
                    } else if ((gameTurn + 1) >= gameInfo.mTurns) {
                        gameStatus = Status.FINISHED;
                    } else {
                        gameTurn++;
                    }
                    break;
            }
        }

        if (gameStatus == Status.CORRECT) {
            int score = ((int) pow(gameInfo.mColors, gameInfo.mPegs)) - gameTurn;
            gameBreaker.notifyScore(score);
            StatController.getInstance().addScore(gameInfo.mUser, gameInfo.getGameTitle(),
                    score, gameInfo.getElapsedTime());
        }
    }

    /**
     * Gets color row.
     *
     * @param turn the turn
     * @return the color row
     */
    public ColorRow getColorRow(int turn) {
        return mGuess.get(turn - 1);
    }

    /**
     * Gets control row.
     *
     * @param turn the turn
     * @return the control row
     */
    public ControlRow getControlRow(int turn) {
        return mControl.get(turn - 1);
    }

    /**
     * Gets game status.
     *
     * @return the game status
     */
    public String getGameStatus() {
        return gameStatus.toString();
    }

    /**
     * Gets game title.
     *
     * @return the game title
     */
    public String getGameTitle() {
        return gameInfo.getGameTitle();
    }

    /**
     * Finish game.
     */
    public void finishGame() {
        gameStatus = Status.FINISHED;
    }

    /**
     * The enum Status.
     */
    public enum Status {
        /**
         * Start status.
         */
        START,
        /**
         * Guess status.
         */
        GUESS,
        /**
         * Correct status.
         */
        CORRECT,
        /**
         * Finished status.
         */
        FINISHED
    }

    /**
     * The type Game info.
     */
    public static class GameInfo {
        private String mUser;
        private Player.Role mRole;
        private int mPegs, mColors, mTurns;
        private Date mStart = new Date();

        /**
         * Instantiates a new Game info.
         *
         * @param user   the user
         * @param role   the role
         * @param pegs   the pegs
         * @param colors the colors
         * @param turns  the turns
         */
        public GameInfo(String user, Player.Role role, int pegs, int colors, int turns) {
            mUser = user;
            mRole = role;
            mPegs = pegs;
            mColors = colors;
            mTurns = turns;
        }

        /**
         * Gets game title.
         *
         * @return the game title
         */
        String getGameTitle() {
            return mUser + "-" + mRole + "-" + mStart;
        }

        /**
         * Gets elapsed time.
         *
         * @return the elapsed time
         */
        long getElapsedTime() {
            return (new Date().getTime() - mStart.getTime()) / 1000;
        }

    }
}
