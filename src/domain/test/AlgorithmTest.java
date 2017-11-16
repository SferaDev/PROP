package domain.test;

import domain.controller.DomainController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import presentation.controller.receivers.TerminalReceiver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AlgorithmTest {
    private final String computerName;
    private final Integer pegs;
    private final Integer colors;

    public AlgorithmTest(String computerName, Integer pegs, Integer colors) {
        this.computerName = computerName;
        this.pegs = pegs;
        this.colors = colors;
    }

    @Parameterized.Parameters(name = "Computer: {0} | Pegs: {1} | Colors: {2}")
    public static Collection parameters() {
        return Arrays.asList(new Object[][]{
                {"FiveGuessComputer", 4, 6},
                {"GeneticComputer", 4, 6},
                {"FiveGuessComputer", 5, 6},
                {"GeneticComputer", 7, 7}
        });
    }

    @Before
    public void setUp() throws Exception {
        DomainController.getInstance().setGameInterface(new TerminalReceiver());
        DomainController.getInstance().setDebugBuild(true);
    }

    @Test
    public void runTestGame() throws Exception {
        DomainController.getInstance().getGameController().startNewGame(computerName, pegs, colors, 12);
        String gameStatus = DomainController.getInstance().getGameController().getGameStatus();
        DomainController.getInstance().getGameInterface().outputMessage(gameStatus + "\n");
        Assert.assertTrue(gameStatus.equals("CORRECT"));
    }
}