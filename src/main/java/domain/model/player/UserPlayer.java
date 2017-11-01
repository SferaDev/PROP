package domain.model.player;

import domain.controller.MainController;
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
        return MainController.getInstance().getGameInterface().inputColorRow(pegs, colors);
    }

    @Override
    public ColorRow breakerGuess(int pegs, int colors) {
        return MainController.getInstance().getGameInterface().inputColorRow(pegs, colors);
    }

    @Override
    public ControlRow scoreGuess(ColorRow guess) {
        MainController.getInstance().getGameInterface().outputColorRow(guess);
        return MainController.getInstance().getGameInterface().inputControlRow(guess.size());
    }

    @Override
    public void receiveControl(ControlRow control) {
        MainController.getInstance().getGameInterface().outputControlRow(control);
    }
}
