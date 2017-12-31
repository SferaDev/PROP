import domain.controller.DomainController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

/**
 * The type Algorithm test.
 *
 * @author Alexis Rico Carreto
 */
public class AlgorithmTest1 extends AlgorithmTest {
    private String computerName;
    private Integer pegs;
    private Integer colors;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() {
        DomainController.getInstance().setGameInterface(new DebugReceiver());
        DomainController.getInstance().setDebugBuild(true);

        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: CPU vs CPU");
        builder.addOption(Constants.NEW_GAME_FIVEGUESS, () -> setComputerName("FiveGuessComputer"));
        builder.addOption(Constants.NEW_GAME_GENETIC, () -> setComputerName("GeneticComputer"));
        builder.onExitGoBackToStart(true);
        builder.execute();

        TerminalUtils.getInstance().printLine("Introdueix Fitxes:");
        pegs = TerminalUtils.getInstance().readInteger();
        TerminalUtils.getInstance().printLine("Introdueix Colors:");
        colors = TerminalUtils.getInstance().readInteger();
    }

    /**
     * After.
     *
     * @throws Exception the exception
     */
    @After
    public void after() {
        TerminalUtils.getInstance().pressEnterToContinue();
    }

    /**
     * Run test game.
     *
     * @throws Exception the exception
     */
    @Test
    public void runTestGame() throws Exception {
        super.newGame(computerName, pegs, colors);
    }

    private void setComputerName(String computerName) {
        this.computerName = computerName;
    }
}