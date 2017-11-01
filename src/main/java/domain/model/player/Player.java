package domain.model.player;

import domain.model.row.ColorRow;
import domain.model.row.ControlRow;

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

    public abstract ColorRow makerGuess(int pegs, int colors);

    public abstract ColorRow breakerGuess(int pegs, int colors);

    public abstract ControlRow scoreGuess(ColorRow guess);

    public abstract void receiveControl(ControlRow control);

    public enum Role {
        BREAKER, MAKER
    }
}
