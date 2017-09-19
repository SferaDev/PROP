package domain.model.player;

import domain.model.Game;
import domain.model.player.Player;
import domain.model.Role;
import domain.model.Row;
import domain.model.peg.ColorPeg;
import domain.model.peg.ControlPeg;

public class ComputerPlayer extends Player {
    public ComputerPlayer(Role role, Game game) {
        super(role, game);
    }

    @Override
    public String getName() {
        return "CPU";
    }

    @Override
    public Row<ColorPeg> makeInitialGuess() {
        return null;
    }

    @Override
    public Row<ColorPeg> makeGuess() {
        return null;
    }

    @Override
    public Row<ControlPeg> scoreGuess(Row<ColorPeg> guess) {
        return null;
    }
}
