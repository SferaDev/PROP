package domain.model.player.computer;

import domain.controller.MainController;
import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;
import domain.model.player.ComputerPlayer;

public class DummyComputer extends ComputerPlayer {
    public DummyComputer(Role role) {
        super(role);
    }

    Row<ColorPeg> correctMakerGuess;

    @Override
    public String getName() {
        return "Dummy";
    }

    @Override
    public Row<ColorPeg> makerGuess(int pegs, int colors) {
        correctMakerGuess = randomRow(pegs, colors);
        if (MainController.DEBUG) {
            System.out.println("DummyComputer: Correct guess is:");
            StringBuilder output = new StringBuilder();
            for (ColorPeg peg : correctMakerGuess) {
                output.append(peg.getColor()).append(" ");
            }
            System.out.println(output.toString());
        }
        return correctMakerGuess;
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
