package domain.test.drivers;

import domain.model.User;
import domain.model.exceptions.CommandInterruptException;
import domain.model.exceptions.FinishGameException;
import presentation.controller.TerminalController;
import presentation.utils.TerminalMenuBuilder;

public class DriverUser {
    private static TerminalMenuBuilder terminalMenuBuilder = new TerminalMenuBuilder();
    private static final TerminalController terminalController = TerminalController.getInstance();

    public static void main(String args[]) throws FinishGameException, CommandInterruptException {
        terminalMenuBuilder.addTitle("Menu DriverUser:");
        terminalMenuBuilder.addOption("Probar creadora", DriverUser::case1);
        terminalMenuBuilder.addOption("Sortir",terminalMenuBuilder::finishExecution);
        terminalMenuBuilder.execute();
    }

    private static void case1() {
        terminalController.printLine("Introdueixi nom d'usuari:");
        String nameUser = terminalController.readString();
        terminalController.printLine("Introdueixi pasword:");
        String password = terminalController.readString();
        User u = new  User(nameUser, password);
        terminalController.printLine("S'ha creat l'usuari " + u.getName() + " amb contrassenya " + u.getPassword() );

    }
}
