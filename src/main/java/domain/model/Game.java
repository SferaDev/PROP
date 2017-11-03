package domain.model;

import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;
import java.util.Date;

public class Game {
    private Status gameStatus;

    private Player gameMaker, gameBreaker;

    private GameInfo gameInfo;

    private ColorRow correctGuess;
    private ArrayList<ColorRow> mGuess = new ArrayList<>();
    private ArrayList<ControlRow> mControl = new ArrayList<>();

    private int gameTurn = 1;

    public Game(Player maker, Player breaker, GameInfo info) {
        gameMaker = maker;
        gameBreaker = breaker;
        gameInfo = info;

        gameStatus = Status.START;
    }

    public void startGame() {
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
    }

    public ColorRow getColorRow(int turn) {
        return mGuess.get(turn - 1);
    }

    public ControlRow getControlRow(int turn) {
        return mControl.get(turn - 1);
    }

    @Override
    public String toString() {
        return gameInfo.mGameTitle;
    }

    public String getGameStatus() {
        return gameStatus.toString();
    }

    public enum Status {
        START, GUESS, CORRECT, FINISHED
    }

    public static class GameInfo {
        private String mGameTitle;
        private int mPegs, mColors, mTurns;

        public GameInfo(String name, Player.Role role, int pegs, int colors, int turns) {
            mGameTitle = name + "-" + role + "-" + new Date().toString();
            mPegs = pegs;
            mColors = colors;
            mTurns = turns;
        }
    }
}
