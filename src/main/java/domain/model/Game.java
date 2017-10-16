package domain.model;

import domain.controller.MainController;
import domain.model.peg.ColorPeg;
import domain.model.player.ComputerPlayer;
import domain.model.player.Player;

import java.util.Date;

public class Game {
    private Date startTime = new Date();

    private Player gameMaker;
    private Player gameBreaker;


    private String gameMakerName;
    private String gameBreakerName;

    private Row<ColorPeg> correctGuess;

    private Board gameBoard;

    public Game(Player player) {
        Role playerRole = player.getPlayerRole();
        gameBreaker = playerRole == Role.BREAKER ?
                player : new ComputerPlayer(Role.MAKER, this);
        gameMaker = playerRole == Role.MAKER ?
                player : new ComputerPlayer(Role.BREAKER, this);

        gameMakerName = gameMaker.getName();
        gameBreakerName = gameBreaker.getName();

    }

    private InputOutput getGameInterface() {
        return MainController.getInstance().getGameInterface();
    }
}
