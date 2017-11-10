package domain.model.player.computer;

import domain.InputOutput;
import domain.controller.DomainController;
import domain.model.player.ComputerPlayer;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

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
    public void notifyInvalidInput() {
        // Should never happen
    }

    @Override
    public ControlRow scoreGuess(ColorRow guess) {
        ControlRow score = super.scoreGuess(guess);
        DomainController domainController = DomainController.getInstance();
        if (domainController.isDebugBuild()) {
            InputOutput gameInterface = domainController.getGameInterface();
            gameInterface.outputColorRow(guess.toString());
            gameInterface.outputControlRow(score.getBlacks(), score.getWhites());
            gameInterface.outputMessage("");
        }
        return score;
    }

    @Override
    public ColorRow makerGuess(int pegs, int colors) {
        ColorRow correctGuess = super.makerGuess(pegs, colors);
        DomainController domainController = DomainController.getInstance();
        if (DomainController.getInstance().isDebugBuild()) {
            InputOutput gameInterface = domainController.getGameInterface();
            gameInterface.outputMessage("Pegs: " + pegs);
            gameInterface.outputMessage("Colors: " + colors);
            gameInterface.outputMessage("Correct Guess is:");
            gameInterface.outputColorRow(correctGuess.toString());
            gameInterface.outputMessage("");
        }
        return correctGuess;
    }

    // TODO: Check if it works
    public ColorRow guessHelp(ControlRow status, int pegs, int colors) {
        ColorRow helpAttempt;
        ControlRow controlRow;
        do {
            helpAttempt = randomRow(pegs, colors);
            controlRow = scoreGuess(helpAttempt);
        } while (controlRow.getBlacks() < status.getBlacks() || controlRow.getWhites() < status.getWhites());
        return helpAttempt;
    }
}
