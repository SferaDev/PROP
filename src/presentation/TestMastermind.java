package presentation;

import domain.controller.DomainController;
import domain.test.AlgorithmTest;
import domain.test.DebugReceiver;
import domain.test.drivers.*;
import domain.test.unit.GameUnitTest;
import org.junit.runner.JUnitCore;
import presentation.utils.TerminalMenuBuilder;

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
        builder.addOption("Algorithm: Executa 4 partides CPU vs CPU", () -> runJUnit(AlgorithmTest.class));
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
        junit.run(className);
    }

    @Override
    public void setInputOutPut() {
        DomainController.getInstance().setGameInterface(new DebugReceiver());
    }
}
