package domain.model;

import domain.controller.MainController;
import domain.model.peg.ColorPeg;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.GeneticComputer;

import java.util.Date;

public class Game {
    private Date startTime = new Date();
    private String ownerName;
    private Role ownerRole;

    private Status gameStatus;

    private Player gameMaker, gameBreaker;

    private Row<ColorPeg> correctGuess;

    private int mPegs, mColors, mTurns;

    public Game(Player player, int pegs, int colors, int turns) {
        ownerName = player.getName();
        ownerRole = player.getPlayerRole();

        gameBreaker = ownerRole == Role.BREAKER ?
                player : new GeneticComputer(Role.MAKER, mPegs, mColors, mTurns);
        gameMaker = ownerRole == Role.MAKER ?
                player : new GeneticComputer(Role.BREAKER, mPegs, mColors, mTurns);

        mPegs = pegs;
        mColors = colors;
        mTurns = turns;

        gameStatus = Status.START;
    }

    @Override
    public String toString() {
        return ownerName + "-" + ownerRole + "-" + startTime.toString();
    }

    public void startGame() {
        while (gameStatus != Status.CORRECT && gameStatus != Status.QUIT) {
            switch (gameStatus) {
                case START:
                    correctGuess = gameMaker.breakerInitialGuess(mPegs, mColors);
                    gameStatus = Status.GUESS;
                    break;
                case GUESS:
                    Row<ColorPeg> input = gameBreaker.breakerGuess(mPegs, mColors);
                    gameStatus = input.equals(correctGuess) ? Status.CORRECT : Status.RESPONSE;
                    break;
                case RESPONSE:
                    break;
            }
        }
    }

    private void setStatus(Status status) {
        gameStatus = status;
    }
}
