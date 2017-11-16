package domain.test.drivers;

import domain.controller.DomainController;
import domain.model.Game;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalMenuBuilder;

public class GameDriver {
    private static final TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
    private static final Game.GameInfo gameInfoMaker = new Game.GameInfo("testUser", Player.Role.MAKER, 4, 6, 12);
    private static final Game.GameInfo gameInfoBreaker = new Game.GameInfo("testUser", Player.Role.BREAKER, 4, 6, 12);

    public static void main(String args[]) throws FinishGameException, CommandInterruptException {
        terminalMenuBuilder.addTitle("Menu GameDriver:");
        terminalMenuBuilder.addOption("Probar game com a Maker", GameDriver::case1);
        terminalMenuBuilder.addOption("Probar Game coma a Breaker", GameDriver::case2);
        terminalMenuBuilder.addOption("Sortir", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case2() {
        Player p1 = new UserPlayer("testPlayer1", Player.Role.BREAKER);
        Player p2 = new DummyComputer(Player.Role.MAKER);
        Game g = new Game(p1, p2, gameInfoBreaker);
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());
        try {
            g.startGame();
        } catch (FinishGameException ignored) {
        }

    }

    private static void case1() {
        Player p1 = new UserPlayer("testPlayer1", Player.Role.MAKER);
        Player p2 = new DummyComputer(Player.Role.BREAKER);
        Game g = new Game(p1, p2, gameInfoMaker);
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());
        try {
            g.startGame();
        } catch (FinishGameException ignored) {
        }
    }

}
