package domain.model.player;

import domain.controller.MainController;
import domain.model.Role;
import domain.model.Row;
import domain.model.User;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public class UserPlayer extends Player {

    private User parentUser;

    public UserPlayer(User user, Role role) {
        super(role);
        parentUser = user;
    }

    @Override
    public String getName() {
        return parentUser.getName();
    }

    @Override
    public Row<ColorPeg> makerGuess(int pegs, int colors) {
        return MainController.getInstance().getGameInterface().inputColorRow(pegs, colors);
    }

    @Override
    public Row<ColorPeg> breakerInitialGuess(int pegs, int colors) {
        return MainController.getInstance().getGameInterface().inputColorRow(pegs, colors);
    }

    @Override
    public Row<ColorPeg> breakerGuess(int pegs, int colors) {
        return MainController.getInstance().getGameInterface().inputColorRow(pegs, colors);
    }

    @Override
    public Row<ControlPeg> scoreGuess(Row<ColorPeg> guess) {
        MainController.getInstance().getGameInterface().outputColorRow(guess);
        return MainController.getInstance().getGameInterface().inputControlRow(guess.size());
    }

    @Override
    public void receiveControl(Row<ControlPeg> control) {
        MainController.getInstance().getGameInterface().outputControlRow(control);
    }
}
