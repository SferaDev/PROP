import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TimeUtils;

public class TerminalUtils {
    private void runJUnit(Class className) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(className);
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind: JUnit Test for " + className.getName());
        builder.addDescription(String.format("%-15.15s %-15.15s", "Successful:", result.wasSuccessful()));
        builder.addDescription(String.format("%-15.15s %-15.15s", "Total Count:", result.getRunCount()));
        builder.addDescription(String.format("%-15.15s %-15.15s", "Failure Count:", result.getFailureCount()));
        builder.addDescription(String.format("%-15.15s %-15.15s", "Ignored Count:", result.getIgnoreCount()));
        builder.addDescription(String.format("%-15.15s %-15.15s", "Run time:", TimeUtils.timestampToString(result.getRunTime())));
        builder.addOption("Enrere", builder::finishExecution);
        builder.execute();
    }
}
