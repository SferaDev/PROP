package domain.test.drivers;

import domain.model.User;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.utils.TerminalMenuBuilder;
import presentation.utils.TerminalUtils;

public class UserDriver {
    private static final TerminalUtils terminalUtils = TerminalUtils.getInstance();

    public static void main(String args[]) throws FinishGameException, CommandInterruptException {
        TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
        terminalMenuBuilder.addTitle("Menu UserDriver:");
        terminalMenuBuilder.addOption("Probar creadora", UserDriver::case1);
        terminalMenuBuilder.addOption("Sortir", terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case1() {
        terminalUtils.printLine("Introdueixi nom d'usuari:");
        String nameUser = terminalUtils.readString();
        terminalUtils.printLine("Introdueixi pasword:");
        String password = terminalUtils.readString();
        User u = new User(nameUser, password);
        terminalUtils.printLine("S'ha creat l'usuari " + u.getName() + " amb contrassenya " + u.getPassword());

    }
}
