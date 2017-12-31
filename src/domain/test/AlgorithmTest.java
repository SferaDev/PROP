package domain.test;

import domain.controller.DomainController;
import org.junit.Assert;
import presentation.utils.TerminalUtils;

public class AlgorithmTest {
    void newGame(String computerName, int pegs, int colors) {
        TerminalUtils.getInstance().clearScreen();
        DomainController.getInstance().getGameController().startNewGame(computerName, pegs, colors, 12);
        String gameStatus = DomainController.getInstance().getGameController().getGameStatus();
        DomainController.getInstance().getGameInterface().outputMessage("\n" + gameStatus + "\n");
        Assert.assertTrue(gameStatus.equals("CORRECT"));
    }
}
