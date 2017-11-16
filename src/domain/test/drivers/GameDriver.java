package domain.test.drivers;

import domain.controller.DomainController;
import domain.model.Game;
import domain.model.exceptions.FinishGameException;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;
import presentation.controller.receivers.TerminalReceiver;
import presentation.utils.TerminalMenuBuilder;

public class GameDriver {

    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Mastermind: GameDriver");
        terminalMenuBuilder.addDescription("Podràs provar l'entrada i sortida de dades amb la classe Game.");
        terminalMenuBuilder.addDescription("L'execució terminarà quan arribis al torn 12.");
        terminalMenuBuilder.addOption("Provar Game com a Maker", GameDriver::testGameMaker);
        terminalMenuBuilder.addOption("Provar Game com a Breaker", GameDriver::testGameBreaker);
        terminalMenuBuilder.addOption("Enrere", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void testGameMaker() {
        Player p1 = new UserPlayer("testPlayerMaker", Player.Role.MAKER);
        Player p2 = new DummyComputer(Player.Role.BREAKER);
        Game.GameInfo gameInfoMaker = new Game.GameInfo("testPlayerMaker", Player.Role.MAKER,
                4, 6, 12);
        Game g = new Game(p1, p2, gameInfoMaker);
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());
        try {
            g.startGame();
        } catch (FinishGameException ignored) {
        }
    }

    private static void testGameBreaker() {
        Player p1 = new UserPlayer("testPlayerBreaker", Player.Role.BREAKER);
        Player p2 = new DummyComputer(Player.Role.MAKER);
        Game.GameInfo gameInfoBreaker = new Game.GameInfo("testPlayerBreaker", Player.Role.BREAKER,
                4, 6, 12);
        Game g = new Game(p1, p2, gameInfoBreaker);
        DomainController domainController = DomainController.getInstance();
        domainController.setGameInterface(new TerminalReceiver());
        try {
            g.startGame();
        } catch (FinishGameException ignored) {
        }

    }

}
