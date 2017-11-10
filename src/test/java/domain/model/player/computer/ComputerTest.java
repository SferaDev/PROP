package domain.model.player.computer;

import domain.controller.DomainController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import presentation.model.TerminalInputOutput;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ComputerTest {
    private String computerName;
    private Integer pegs;
    private Integer colors;

    public ComputerTest(String computerName, Integer pegs, Integer colors) {
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
        DomainController.getInstance().setGameInterface(new TerminalInputOutput());
        DomainController.getInstance().setDebugBuild(true);
    }

    @Test
    public void runTestGame() throws Exception {
        DomainController.getInstance().startNewGame(computerName, pegs, colors, 12);
        String gameStatus = DomainController.getInstance().getGameStatus();
        DomainController.getInstance().getGameInterface().outputMessage(gameStatus + "\n");
        Assert.assertTrue(gameStatus.equals("CORRECT"));
    }
}