package domain.test.drivers;

import domain.model.User;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

public class UserDriver {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();

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
        User u = new User(nameUser, password);
        terminalUtils.printLine("S'ha creat l'usuari " + u.getName() + " amb contrassenya " + u.getPassword());
        TerminalUtils.getInstance().pressEnterToContinue();
    }
}
