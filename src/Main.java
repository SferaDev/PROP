import presentation.Mastermind;
import presentation.TerminalMastermind;
import presentation.TestMastermind;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;

/**
 * The type Master mind.
 *
 * @author Alexis Rico Carreto
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        TerminalMenuBuilder builder = new TerminalMenuBuilder();
        builder.addTitle(Constants.APP_TITLE);
        builder.addOption("Mode Usuari", () -> launch(new TerminalMastermind()));
        builder.addOption("Mode Test", () -> launch(new TestMastermind()));
        builder.addOption("Surt", builder::finishExecution);
        builder.execute();
    }

    private static void launch(Mastermind application) {
        application.setInputOutPut();
        application.startApplication();
    }
}
