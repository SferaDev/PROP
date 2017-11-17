package presentation;

import domain.controller.DomainController;
import domain.test.AlgorithmTest1;
import domain.test.AlgorithmTest4;
import domain.test.DebugReceiver;
import domain.test.drivers.*;
import domain.test.unit.GameUnitTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

/**
 * The type Test mastermind.
 *
 * @author Oriol Borrell Roig
 */
public class TestMastermind implements Mastermind {
    @Override
    public void startApplication() {
        DomainController.getInstance().setDebugBuild(true);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: Tests");
        builder.addOption("Algorithm: Executa 1 partida CPU vs CPU", () -> runJUnit(AlgorithmTest1.class));
        builder.addOption("Algorithm: Executa 4 partides CPU vs CPU", () -> runJUnit(AlgorithmTest4.class));
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
        builder.addOption("Enrere", builder::finishExecution);
        builder.execute();
        DomainController.getInstance().setDebugBuild(false);
    }

    private void runJUnit(Class className) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(className);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: JUnit Test for " + className.getName());
        builder.addDescription(String.format("%-15.15s %-15.15s", "Successful:", result.wasSuccessful()));
        builder.addDescription(String.format("%-15.15s %-15.15s", "Total Count:", result.getRunCount()));
        builder.addDescription(String.format("%-15.15s %-15.15s", "Failure Count:", result.getFailureCount()));
        builder.addDescription(String.format("%-15.15s %-15.15s", "Ignored Count:", result.getIgnoreCount()));
        builder.addDescription(String.format("%-15.15s %-15.15s", "Run time:", TerminalUtils.timestampToString(result.getRunTime())));
        builder.addOption("Enrere", builder::finishExecution);
        builder.execute();
    }

    @Override
    public void setInputOutPut() {
        DomainController.getInstance().setGameInterface(new DebugReceiver());
    }
}
