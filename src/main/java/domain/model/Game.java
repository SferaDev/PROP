package domain.model;

import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.DummyComputer;
import domain.model.player.computer.GeneticComputer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

import java.util.ArrayList;
import java.util.Date;

public class Game {
    private String gameTitle;

    private Status gameStatus;

    private Player gameMaker, gameBreaker;

    private GameInfo gameInfo;

    private ColorRow correctGuess;
    private ArrayList<ColorRow> mGuess = new ArrayList<>();
    private ArrayList<ControlRow> mControl = new ArrayList<>();

    private int gameTurn = 1;

    public Game(Player player, GameInfo info) {
        String ownerName = player.getName();
        Player.Role ownerRole = player.getPlayerRole();
        Date startTime = new Date();
        gameTitle = ownerName + "-" + ownerRole + "-" + startTime.toString();

        switch (ownerRole) {
            case BREAKER:
                gameBreaker = player;
                gameMaker = new DummyComputer(Player.Role.MAKER);
                break;
            case MAKER:
                gameBreaker = new GeneticComputer(Player.Role.BREAKER, info.mPegs, info.mColors, info.mTurns);
                gameMaker = player;
                break;
        }

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
                        // TODO: Notify User is a liar
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
        return gameTitle;
    }

    public enum Status {
        START, GUESS, CORRECT, FINISHED
    }

    public static class GameInfo {
        int mPegs, mColors, mTurns;

        public GameInfo(int pegs, int colors, int turns) {
            mPegs = pegs;
            mColors = colors;
            mTurns = turns;
        }
    }
}
