package domain.model;

import domain.controller.MainController;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;
import domain.model.player.computer.DummyComputer;
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

    private int gameTurn = 1;

    public Game(Player player, int pegs, int colors, int turns) {
        ownerName = player.getName();
        ownerRole = player.getPlayerRole();

        gameBreaker = ownerRole == Role.BREAKER ?
                player : new DummyComputer(Role.MAKER);
        gameMaker = ownerRole == Role.MAKER ?
                player : new DummyComputer(Role.BREAKER);

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
                    correctGuess = gameMaker.makerGuess(mPegs, mColors);
                    gameStatus = Status.GUESS;
                    break;
                case GUESS:
                    Row<ColorPeg> input = gameBreaker.breakerGuess(mPegs, mColors);
                    Row<ControlPeg> control = gameMaker.scoreGuess(input);
                    gameBreaker.receiveControl(control);
                    // TODO: Check if is a liar!
                    gameStatus = input.equals(correctGuess) ? Status.CORRECT : Status.GUESS;
                    gameTurn++;
                    break;
            }
        }
    }

    private void setStatus(Status status) {
        gameStatus = status;
    }
}
