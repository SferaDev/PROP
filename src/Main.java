import presentation.TerminalMastermind;
import presentation.TestMastermind;
import presentation.utils.TerminalMenuBuilder;

/**
 * The type Master mind.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle("Mastermind");
        builder.addOption("Launch", Main::launchTerminal);
        builder.addOption("Test menu", Main::launchTest);
        builder.addOption("Quit", builder::finishExecution);
        builder.execute();
    }

    private static void launchTest() {
        TestMastermind application = new TestMastermind();
        application.startApplication();
    }

    private static void launchTerminal() {
        TerminalMastermind application = new TerminalMastermind();
        application.startApplication();
    }

}
