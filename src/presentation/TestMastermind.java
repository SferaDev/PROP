package presentation;

import domain.test.AlgorithmTest;
import domain.test.drivers.*;
import domain.test.unit.GameUnitTest;
import org.junit.runner.JUnitCore;
import presentation.utils.TerminalMenuBuilder;

public class TestMastermind implements Mastermind {
    @Override
    public void startApplication() {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: Tests");
        builder.addOption("Algorithm: Run 4 automated CPU vs CPU games", () -> runJUnit(AlgorithmTest.class));
        builder.addOption("JUnit: Game", () -> runJUnit(GameUnitTest.class));
        builder.addOption("Driver: Game", () -> GameDriver.main(null));
        builder.addOption("Driver: User", () -> UserDriver.main(null));
        builder.addOption("Driver: ComputerPlayer", () -> ComputerPlayerDriver.main(null));
        builder.addOption("Driver: UserPlayer", () -> UserPlayerDriver.main(null));
        builder.addOption("Driver: DummyComputer", () -> DummyComputerDriver.main(null));
        builder.addOption("Driver: FiveGuessComputer", () -> FiveGuessComputerDriver.main(null));
        builder.addOption("Driver: GeneticComputer", () -> GeneticComputerDriver.main(null));
        builder.addOption("Driver: ColorRow", () -> ColorRowDriver.main(null));
        builder.addOption("Driver: ControlRow", () -> ControlRowDriver.main(null));
        builder.addOption("Back", builder::finishExecution);
        builder.execute();
    }

    private void runJUnit(Class className) {
        JUnitCore junit = new JUnitCore();
        junit.run(className);
    }

    @Override
    public void setInputOutPut() {
        // No action needed
    }
}
