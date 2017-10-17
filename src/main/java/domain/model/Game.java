package domain.model;

import domain.controller.MainController;
import domain.model.peg.ColorPeg;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;

import java.util.Date;

public class Game {
    private Date startTime = new Date();
    private String ownerName;
    private Role ownerRole;

    private Player gameMaker;
    private Player gameBreaker;

    private Row<ColorPeg> correctGuess;

    private Board gameBoard;

    public Game(Player player) {
        ownerName = player.getName();
        ownerRole = player.getPlayerRole();

        gameBreaker = ownerRole == Role.BREAKER ?
                player : new ComputerPlayer(Role.MAKER, this);
        gameMaker = ownerRole == Role.MAKER ?
                player : new ComputerPlayer(Role.BREAKER, this);
    }

    private InputOutput getGameInterface() {
        return MainController.getInstance().getGameInterface();
    }

    @Override
    public String toString() {
        return ownerName + "-" + ownerRole + "-" + startTime.toString();
    }
}
