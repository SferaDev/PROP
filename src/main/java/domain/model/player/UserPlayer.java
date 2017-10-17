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
    public Row<ColorPeg> makeInitialGuess(int size) {
        return MainController.getInstance().getGameInterface().inputColorRow(size);
    }

    @Override
    public Row<ColorPeg> makeGuess(int size) {
        return MainController.getInstance().getGameInterface().inputColorRow(size);
    }

    @Override
    public Row<ControlPeg> scoreGuess(Row<ColorPeg> guess) {
        return MainController.getInstance().getGameInterface().inputControlRow(guess.size());
    }
}
