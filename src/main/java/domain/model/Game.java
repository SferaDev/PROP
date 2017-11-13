package domain.model;

import domain.controller.StatController;
import domain.model.exceptions.FinishGameException;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;
import java.util.Date;

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
        // Depending the Role, set the player attributes
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
                    actionStart();
                    break;
                case GUESS:
                    actionGuess();
                    break;
                case CONTROL:
                    actionControl();
                    break;
                case CHECK:
                    actionCheck();
                    break;
                case SCORE:
                    actionScore();
                    break;
            }
        }
    }

    private void actionScore() {
        if (gameBreaker instanceof UserPlayer) {
            // Notify the breaker his score
            int score = ((int) Math.pow(gameInfo.mColors, gameInfo.mPegs)) * gameTurn;
            gameBreaker.notifyScore(score);
            StatController.getInstance().addScore(gameInfo.mUser, gameInfo.getGameTitle(),
                    score, gameInfo.getElapsedTime());
        }
        gameStatus = Status.CORRECT;
    }

    private void actionCheck() {
        // Update gameStatus
        if (mGuess.get(mGuess.size() - 1).equals(correctGuess)) {
            gameStatus = Status.SCORE;
        } else if ((gameTurn + 1) >= gameInfo.mTurns) {
            gameStatus = Status.FINISHED;
        } else {
            gameTurn++;
            gameStatus = Status.GUESS;
        }
    }

    private void actionControl() throws FinishGameException {
        // Ask Maker for a control of the input guess
        ControlRow control = gameMaker.scoreGuess(mGuess.get(mGuess.size() - 1));
        ControlRow correctControl = ComputerPlayer.compareGuess(correctGuess, mGuess.get(mGuess.size() - 1));

        // If Maker is lying, notify him
        if (!correctControl.equals(control)) {
            gameMaker.notifyInvalidInput();
        }

        // Send Breaker the correct control and store it
        gameBreaker.receiveControl(correctControl);
        mControl.add(correctControl);
        gameStatus = Status.CHECK;
    }

    private void actionGuess() throws FinishGameException {
        // Ask Breaker an input guess and store it
        ColorRow input = gameBreaker.breakerGuess(gameInfo.mPegs, gameInfo.mColors);
        mGuess.add(input);
        gameStatus = Status.CONTROL;
    }

    private void actionStart() throws FinishGameException {
        // Ask Maker the correct guess
        correctGuess = gameMaker.makerGuess(gameInfo.mPegs, gameInfo.mColors);
        gameStatus = Status.GUESS;
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

    public void provideHelp() {
        // TODO: Elena continua aqui
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
         * Control status.
         */
        CONTROL,
        /**
         * Check status.
         */
        CHECK,
        /**
         * Score status.
         */
        SCORE,
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
    public static class GameInfo implements java.io.Serializable {
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
            return mUser + "-" + mRole + "-" + mPegs + "-" + mColors + "-" + mStart;
        }

        /**
         * Gets elapsed time in ms.
         *
         * @return the elapsed time
         */
        long getElapsedTime() {
            return (new Date().getTime() - mStart.getTime());
        }

    }
}
