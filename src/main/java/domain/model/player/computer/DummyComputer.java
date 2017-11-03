package domain.model.player.computer;

import domain.controller.DomainController;
import domain.controller.InputOutput;
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
        DomainController domainController = DomainController.getInstance();
        if (domainController.isDebugBuild()) {
            InputOutput gameInterface = domainController.getGameInterface();
            gameInterface.outputColorRow(guess);
            gameInterface.outputControlRow(score);
            gameInterface.outputMessage("");
        }
        return score;
    }

    @Override
    public ColorRow makerGuess(int pegs, int colors) {
        ColorRow initialGuess = super.makerGuess(pegs, colors);
        DomainController domainController = DomainController.getInstance();
        if (DomainController.getInstance().isDebugBuild()) {
            InputOutput gameInterface = domainController.getGameInterface();
            gameInterface.outputMessage("Pegs: " + pegs);
            gameInterface.outputMessage("Colors: " + colors);
            gameInterface.outputMessage("Correct Guess is:");
            gameInterface.outputColorRow(initialGuess);
            gameInterface.outputMessage("");
        }
        return initialGuess;
    }
}
