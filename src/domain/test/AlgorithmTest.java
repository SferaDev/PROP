package domain.test;

import domain.controller.DomainController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import presentation.utils.TerminalUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * The type Algorithm test.
 *
 * @author Alexis Rico Carreto
 */
@RunWith(Parameterized.class)
public class AlgorithmTest {
    private final String computerName;
    private final Integer pegs;
    private final Integer colors;

    /**
     * Instantiates a new Algorithm test.
     *
     * @param computerName the computer name
     * @param pegs         the pegs
     * @param colors       the colors
     */
    public AlgorithmTest(String computerName, Integer pegs, Integer colors) {
        this.computerName = computerName;
        this.pegs = pegs;
        this.colors = colors;
    }

    /**
     * Parameters collection.
     *
     * @return the collection
     */
    @Parameterized.Parameters(name = "Computer: {0} | Pegs: {1} | Colors: {2}")
    public static Collection parameters() {
        return Arrays.asList(new Object[][]{
                {"FiveGuessComputer", 5, 5},
                {"GeneticComputer", 6, 6},
                {"FiveGuessComputer", 5, 6},
                {"GeneticComputer", 6, 7}
        });
    }

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        DomainController.getInstance().setGameInterface(new DebugReceiver());
        DomainController.getInstance().setDebugBuild(true);
    }

    /**
     * Run test game.
     *
     * @throws Exception the exception
     */
    @Test
    public void runTestGame() throws Exception {
        TerminalUtils.getInstance().clearScreen();
        DomainController.getInstance().getGameController().startNewGame(computerName, pegs, colors, 12);
        String gameStatus = DomainController.getInstance().getGameController().getGameStatus();
        DomainController.getInstance().getGameInterface().outputMessage("\n" + gameStatus + "\n");
        Assert.assertTrue(gameStatus.equals("CORRECT"));
        TerminalUtils.getInstance().pressEnterToContinue();
    }
}