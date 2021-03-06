package drivers;

import domain.model.User;
import presentation.controller.LocaleController;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

/**
 * The type User driver.
 *
 * @author Oriol Borrell Roig
 */
public class UserDriver {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Mastermind: UserDriver");
        terminalMenuBuilder.addOption("Provar creadora", UserDriver::testConstructor);
        terminalMenuBuilder.addOption("Enrere", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void testConstructor() {
        terminalUtils.printLine("Introdueixi nom d'usuari:");
        String nameUser = terminalUtils.readString();
        terminalUtils.printLine("Introdueixi pasword:");
        String password = terminalUtils.readString();
        User u = new User(nameUser, password, LocaleController.getInstance().getLanguage().name());
        terminalUtils.printLine("S'ha creat l'usuari " + u.getName() + " amb contrassenya " + u.getPassword());
        TerminalUtils.getInstance().pressEnterToContinue();
    }
}
