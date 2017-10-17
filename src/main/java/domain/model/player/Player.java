package domain.model.player;

import domain.model.Game;
import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public abstract class Player {
    // Role is defined upon creation, only has a getter
    // Protected so it can be accessible on the sub-classes
    protected Role playerRole;

    // Current game is created with Player object, has no getter nor setter
    // Protected so it can be accessible on the sub-classes
    protected Game currentGame;

    public Player(Role role, Game game) {
        currentGame = game;
        playerRole = role;
    }

    public Player(Role role) {
        currentGame = new Game(this);
        playerRole = role;
    }

    public Role getPlayerRole() {
        return playerRole;
    }

    public abstract String getName();

    public abstract Row<ColorPeg> makeInitialGuess(int size);

    public abstract Row<ColorPeg> makeGuess(int size);

    public abstract Row<ControlPeg> scoreGuess(Row<ColorPeg> guess);
}
