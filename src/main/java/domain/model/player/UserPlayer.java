package domain.model.player;

import domain.controller.DomainController;
import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

public class UserPlayer extends Player {

    private String parentUser;

    public UserPlayer(String user, Role role) {
        super(role);
        parentUser = user;
    }

    @Override
    public String getName() {
        return parentUser;
    }

    @Override
    public ColorRow makerGuess(int pegs, int colors) {
        return new ColorRow(DomainController.getInstance().getGameInterface().inputColorRow(pegs, colors));
    }

    @Override
    public ColorRow breakerGuess(int pegs, int colors) {
        return new ColorRow(DomainController.getInstance().getGameInterface().inputColorRow(pegs, colors));
    }

    @Override
    public ControlRow scoreGuess(ColorRow guess) {
        DomainController.getInstance().getGameInterface().outputColorRow(guess.toString());
        int blacks = DomainController.getInstance().getGameInterface().inputControlBlacks(guess.size());
        int whites = DomainController.getInstance().getGameInterface().inputControlWhites(guess.size());
        return new ControlRow(blacks, whites);
    }

    @Override
    public void receiveControl(ControlRow control) {
        DomainController.getInstance().getGameInterface().outputControlRow(control.getBlacks(), control.getWhites());
    }

    @Override
    public void notifyInvalidInput() {
        DomainController.getInstance().getGameInterface().notifyInvalidInput();
    }

    @Override
    public void notifyScore(int score) {
        DomainController.getInstance().getGameInterface().notifyScore(score);
    }
}
