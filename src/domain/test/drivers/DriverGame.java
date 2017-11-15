package domain.test.drivers;

import domain.controller.DomainController;
import domain.model.Game;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import domain.model.player.Player;
import domain.model.player.UserPlayer;
import domain.model.player.computer.DummyComputer;
import presentation.controller.TerminalController;
import presentation.model.TerminalInputOutput;
import presentation.utils.TerminalMenuBuilder;

public class DriverGame {
    private static TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
    private static final TerminalController terminalController = TerminalController.getInstance();
    private static final TerminalInputOutput inputOutput = new TerminalInputOutput();
    private static Game.GameInfo gameInfoMaker = new Game.GameInfo("testUser", Player.Role.MAKER,4, 6, 12 );
    private static Game.GameInfo gameInfoBreaker = new Game.GameInfo("testUser", Player.Role.BREAKER,4, 6, 12 );



    public static void main(String args[]) throws FinishGameException, CommandInterruptException {
        terminalMenuBuilder.addTitle("Menu DriverGame:");
        terminalMenuBuilder.addOption("Probar game com a Maker", DriverGame::case1);
        terminalMenuBuilder.addOption("Probar Game coma a Breaker", DriverGame::case2);

        terminalMenuBuilder.addOption("Demanar una GuessHelp", DriverGame::case3);
        terminalMenuBuilder.addOption("Sortir",terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case3() {

    }

    private static void case2() {
        Player p1 = new UserPlayer("testPlayer1", Player.Role.BREAKER);
        Player p2 = new DummyComputer(Player.Role.MAKER);
        Game g = new Game(p1, p2, gameInfoBreaker);
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
        try {
            g.startGame();
        } catch (FinishGameException e) {
            g = null;
        }

    }

    private static void case1() {
        Player p1 = new UserPlayer("testPlayer1", Player.Role.MAKER);
        Player p2 = new DummyComputer(Player.Role.BREAKER);
        Game g = new Game(p1, p2, gameInfoMaker);
        //Todo: No se de que son aquestes excepcions ni que fer amb elles
        try {
            g.startGame();
        } catch (FinishGameException e) {
            g = null;
        }
    }

}
