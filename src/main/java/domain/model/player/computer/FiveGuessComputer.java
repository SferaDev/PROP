package domain.model.player.computer;

import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.ComputerPlayer;

public class FiveGuessComputer extends ComputerPlayer {
    public FiveGuessComputer(Role role) {
        super(role);
    }

    Row<ColorPeg> correctMakerGuess;

    @Override
    public String getName() {
        return "FiveGuess";
    }

    @Override
    public Row<ColorPeg> makerGuess(int pegs, int colors) {
        correctMakerGuess = randomRow(pegs, colors);
        return correctMakerGuess;
    }

    @Override
    public Row<ColorPeg> breakerInitialGuess(int pegs, int colors) {
        return randomRow(pegs, colors);
    }

    @Override
    public Row<ColorPeg> breakerGuess(int pegs, int colors) {
        return randomRow(pegs, colors);
    }

    @Override
    public Row<ControlPeg> scoreGuess(Row<ColorPeg> guess) {
        return compareGuess(correctMakerGuess, guess);
    }

    @Override
    public void receiveControl(Row<ControlPeg> control) {
        // I don't care, I love it!
    }
}
