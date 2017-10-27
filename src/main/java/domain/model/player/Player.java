package domain.model.player;

import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public abstract class Player {
    // Role is defined upon creation, only has a getter
    // Protected so it can be accessible on the sub-classes
    private Role playerRole;

    Player(Role role) {
        playerRole = role;
    }

    public Role getPlayerRole() {
        return playerRole;
    }

    public abstract String getName();

    public abstract Row<ColorPeg> makerGuess(int pegs, int colors);

    public abstract Row<ColorPeg> breakerGuess(int pegs, int colors);

    public abstract Row<ControlPeg> scoreGuess(Row<ColorPeg> guess);

    public abstract void receiveControl(Row<ControlPeg> control);

    public enum Role {
        BREAKER, MAKER
    }
}
