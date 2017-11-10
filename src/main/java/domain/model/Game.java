package domain.model;

import domain.controller.StatController;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.pow;

public class Game implements java.io.Serializable {
    private Status gameStatus;

    private Player gameMaker, gameBreaker;

    private GameInfo gameInfo;

    private ColorRow correctGuess;
    private ArrayList<ColorRow> mGuess = new ArrayList<>();
    private ArrayList<ControlRow> mControl = new ArrayList<>();

    private int gameTurn = 1;

    private int score = 0;

    public Game(Player user1, Player user2, GameInfo info) {
        switch (user1.getPlayerRole()) {
            case MAKER:
                if (user2.getPlayerRole() == Player.Role.MAKER) {
                    // TODO: Throw exception
                }
                gameMaker = user1;
                gameBreaker = user2;
                break;
            case BREAKER:
                if (user2.getPlayerRole() == Player.Role.BREAKER) {
                    // TODO: Throw exception
                }
                gameBreaker = user1;
                gameMaker = user2;
                break;
        }

        gameInfo = info;

        gameStatus = Status.START;
    }

    public void startGame() {
        while (gameStatus != Status.FINISHED) {
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

                case CORRECT:
                    score = ((int) pow(gameInfo.mColors, gameInfo.mPegs)) - gameTurn;
                    gameBreaker.notifyScore(score);
                    StatController.getInstance().addScore(gameInfo.mUser, gameInfo.getGameTitle(),
                            score, gameInfo.getElapsedTime());
                    gameStatus = Status.FINISHED;
                    break;
            }
        }
    }

    public ColorRow getColorRow(int turn) {
        return mGuess.get(turn - 1);
    }

    public ControlRow getControlRow(int turn) {
        return mControl.get(turn - 1);
    }

    public String getGameStatus() {
        return gameStatus.toString();
    }

    public String getGameTitle() {
        return gameInfo.getGameTitle();
    }

    public enum Status {
        START, GUESS, CORRECT, FINISHED
    }

    public static class GameInfo {
        private String mUser;
        private Player.Role mRole;
        private int mPegs, mColors, mTurns;
        private Date mStart = new Date();

        public GameInfo(String user, Player.Role role, int pegs, int colors, int turns) {
            mUser = user;
            mRole = role;
            mPegs = pegs;
            mColors = colors;
            mTurns = turns;
        }

        public String getGameTitle() {
            return mUser + "-" + mRole + "-" + mStart;
        }

        public long getElapsedTime() {
            return (new Date().getTime() - mStart.getTime()) / 1000;
        }

    }
}
