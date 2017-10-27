package domain.model;

import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.Player;
import domain.model.player.computer.DummyComputer;

import java.util.Date;

public class Game {
    private String gameTitle;

    private Status gameStatus;

    private Player gameMaker, gameBreaker;

    private int mPegs, mColors, mTurns;

    private Row<ColorPeg> correctGuess;

    private int gameTurn = 1;

    public Game(String title, Status status, Player maker, Player breaker, int pegs, int colors, int turns) {

    }

    public Game(Player player, int pegs, int colors, int turns) {
        String ownerName = player.getName();
        Role ownerRole = player.getPlayerRole();
        Date startTime = new Date();
        gameTitle = ownerName + "-" + ownerRole + "-" + startTime.toString();

        switch (ownerRole) {
            case BREAKER:
                gameBreaker = player;
                gameMaker = new DummyComputer(Role.MAKER);
                break;
            case MAKER:
                gameBreaker = new DummyComputer(Role.BREAKER);
                gameMaker = player;
                break;
        }

        mPegs = pegs;
        mColors = colors;
        mTurns = turns;

        gameStatus = Status.START;
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

    @Override
    public String toString() {
        return gameTitle;
    }
}
