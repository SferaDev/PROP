import javafx.application.Application;
import presentation.TerminalMastermind;
import presentation.VisualMastermind;
import presentation.utils.Constants;
import presentation.utils.TerminalMenuBuilder;

/**
 * The type Master mind.
 *
 * @author Alexis Rico Carreto
 */
public class MainApplication {
    public static void main(String[] args) {
        // If run with --terminal open a terminal menu, otherwise launch default Visual app
        if (args.length > 0 && (args[0].equals("-t") || args[0].equals("--terminal"))) {
            TerminalMenuBuilder builder = new TerminalMenuBuilder();
            builder.addTitle(Constants.APP_TITLE);
            builder.addOption("Visual", () -> Application.launch(VisualMastermind.class));
            builder.addOption("Terminal", () -> new TerminalMastermind().startTerminalApplication());
            builder.addOption("Debug", () -> new TerminalMastermind().startDebugApplication());
            builder.addOption("Quit", builder::finishExecution);
            builder.execute();
        } else Application.launch(VisualMastermind.class);
    }
}
