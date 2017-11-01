package domain.model.player.computer;

import domain.controller.DomainController;
import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;
import presentation.model.TerminalInputOutput;

public class DummyComputer extends ComputerPlayer {

    public DummyComputer(Role role) {
        super(role);
    }

    @Override
    public String getName() {
        return "Dummy";
    }

    @Override
    public ColorRow breakerGuess(int pegs, int colors) {
        return randomRow(pegs, colors);
    }

    @Override
    public void receiveControl(ControlRow control) {
        // I don't care, I love it!
    }

    @Override
    public ControlRow scoreGuess(ColorRow guess) {
        ControlRow score = super.scoreGuess(guess);
        if (DomainController.DEBUG) {
            DomainController.getInstance().getGameInterface().outputColorRow(guess);
            DomainController.getInstance().getGameInterface().outputControlRow(score);
            DomainController.getInstance().getGameInterface().outputMessage("");
        }
        return score;
    }

    @Override
    public ColorRow makerGuess(int pegs, int colors) {
        ColorRow initialGuess = super.makerGuess(pegs, colors);
        if (DomainController.DEBUG) {
            DomainController.getInstance().getGameInterface().outputMessage("Pegs: " + pegs);
            DomainController.getInstance().getGameInterface().outputMessage("Colors: " + colors);
            DomainController.getInstance().getGameInterface().outputMessage("Correct Guess is:");
            DomainController.getInstance().getGameInterface().outputColorRow(initialGuess);
            DomainController.getInstance().getGameInterface().outputMessage("");
        }
        return initialGuess;
    }
}
