package domain.test;

import domain.controller.DomainController;
import org.junit.Assert;
import presentation.terminal.utils.TerminalUtils;

public class AlgorithmTest {
    void newGame(String computerName, int pegs, int colors) throws Exception {
        TerminalUtils.getInstance().clearScreen();
        DomainController.getInstance().getGameController().startNewGame(computerName, pegs, colors, 12);
        String gameStatus = DomainController.getInstance().getGameController().getGameStatus();
        DomainController.getInstance().getGameInterface().outputMessage("\n" + gameStatus + "\n");
        Assert.assertTrue(gameStatus.equals("CORRECT"));
    }
}
