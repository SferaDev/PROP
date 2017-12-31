import javafx.application.Application;
import presentation.TerminalMastermind;
import presentation.VisualMastermind;

/**
 * The type Master mind.
 *
 * @author Alexis Rico Carreto
 */
public class MainApplication {
    public static void main(String[] args) {
        // If run with --terminal open a terminal menu, otherwise launch default Visual app
        if (args.length > 0 && (args[0].equals("-t") || args[0].equals("--terminal"))) {
            new TerminalMastermind().startTerminalApplication();
        } else {
            Application.launch(VisualMastermind.class);
        }
    }
}
