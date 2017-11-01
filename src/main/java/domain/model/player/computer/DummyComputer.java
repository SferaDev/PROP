package domain.model.player.computer;

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
}
