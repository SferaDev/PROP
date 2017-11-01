package domain.model.player.computer;

import domain.controller.DomainController;
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

    @Parameterized.Parameters(name="Computer: {0} | Pegs: {1} | Colors: {2}")
    public static Collection parameters() {
        return Arrays.asList(new Object[][] {
                { "FiveGuessComputer", 2, 2 },
                { "GeneticComputer", 2, 2 },
                { "FiveGuessComputer", 3, 3 },
                { "GeneticComputer", 3, 3 },
                { "FiveGuessComputer", 4, 4 },
                { "GeneticComputer", 4, 4 },
                { "FiveGuessComputer", 4, 5 },
                { "GeneticComputer", 4, 5 },
                { "FiveGuessComputer", 4, 6 },
                { "GeneticComputer", 4, 6 },
                { "FiveGuessComputer", 4, 7 },
                { "GeneticComputer", 4, 7 },
                { "FiveGuessComputer", 4, 8 },
                { "GeneticComputer", 4, 8 },
                { "FiveGuessComputer", 4, 9 },
                { "GeneticComputer", 4, 9 },
                { "FiveGuessComputer", 4, 10 },
                { "GeneticComputer", 4, 10 },
                { "FiveGuessComputer", 5, 5 },
                { "GeneticComputer", 5, 5 },
                { "FiveGuessComputer", 5, 6 },
                { "GeneticComputer", 5, 6 },
                { "FiveGuessComputer", 5, 7 },
                { "GeneticComputer", 5, 7 },
                { "FiveGuessComputer", 5, 8 },
                { "GeneticComputer", 5, 8 },
                { "FiveGuessComputer", 5, 9 },
                { "GeneticComputer", 5, 9 },
                { "FiveGuessComputer", 5, 10 },
                { "GeneticComputer", 5, 10 },
                { "FiveGuessComputer", 6, 6 },
                { "GeneticComputer", 6, 6 },
                { "FiveGuessComputer", 6, 7 },
                { "GeneticComputer", 6, 7 },
                { "FiveGuessComputer", 6, 8 },
                { "GeneticComputer", 6, 8 },
                { "FiveGuessComputer", 6, 9 },
                { "GeneticComputer", 6, 9 },
                { "FiveGuessComputer", 6, 10 },
                { "GeneticComputer", 6, 10 },
                { "FiveGuessComputer", 7, 7 },
                { "GeneticComputer", 7, 7 },
                { "FiveGuessComputer", 7, 8 },
                { "GeneticComputer", 7, 8 },
                { "FiveGuessComputer", 7, 9 },
                { "GeneticComputer", 7, 9 },
                { "FiveGuessComputer", 7, 10 },
                { "GeneticComputer", 7, 10 },
                { "FiveGuessComputer", 8, 8 },
                { "GeneticComputer", 8, 8 },
                { "FiveGuessComputer", 8, 9 },
                { "GeneticComputer", 8, 9 },
                { "FiveGuessComputer", 8, 10 },
                { "GeneticComputer", 8, 10 },
                { "FiveGuessComputer", 9, 9 },
                { "GeneticComputer", 9, 9 },
                { "FiveGuessComputer", 9, 10 },
                { "GeneticComputer", 9, 10 },
                { "FiveGuessComputer", 10, 10 },
                { "GeneticComputer", 10, 10 }
        });
    }

    public ComputerTest(String computerName, Integer pegs, Integer colors) {
        this.computerName = computerName;
        this.pegs = pegs;
        this.colors = colors;
    }

    @Before
    public void setUp() throws Exception {
        DomainController.getInstance().setGameInterface(new TerminalInputOutput());
    }

    @Test
    public void runTestGame() throws Exception {
        DomainController.getInstance().startNewGame(computerName, pegs, colors, 1200);
    }
}