import domain.controller.MainController;
import domain.controller.UserController;
import domain.model.User;
import domain.model.io.InputOutput;

import java.util.Scanner;

public class MasterMind {
    private MainController mainController;
    private UserController userController;

    public static void main(String[] args) {
        MasterMind application = new MasterMind();
        // TODO: Args should define if terminal mode or gfx mode
        application.startApplication(true);
    }

    private void startApplication(boolean terminalMode) {
        mainController = MainController.getInstance();
        userController = mainController.getUserController();

        if (terminalMode) {
            showMainMenu();
        } else {
            // GFX Mode
        }
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(Constants.MAIN_MENU_TITLE);
            System.out.println(Constants.REGISTER_MENU + ". " + Constants.REGISTER_MENU_TITLE);
            System.out.println(Constants.LOGIN_MENU    + ". " + Constants.LOGIN_MENU_TITLE);
            System.out.println(Constants.STATS_MENU    + ". " + Constants.STATS_MENU_TITLE);
            System.out.println(Constants.HELP_MENU     + ". " + Constants.HELP_MENU_TITLE);
            System.out.println(Constants.EXIT_MENU     + ". " + Constants.EXIT_MENU_TITLE);

            switch (scanner.nextInt()) {
                case Constants.REGISTER_MENU:
                    register();
                    break;
                case Constants.LOGIN_MENU:
                    login();
                    break;
                case Constants.STATS_MENU:
                    break;
                case Constants.HELP_MENU:
                    break;
                case Constants.EXIT_MENU:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Introdueixi una opció de la llista");
                    break;
            }

        } while (true);
    }

    private void showPlayMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Mastermind: Play Game");
        } while (true);
    }

    private void login() {
        System.out.println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();

        if (!userController.exists(username)) {
            System.err.println(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        User user = null;
        for (int i = 0; user == null && i < 3; i++) {
            if (i > 0) System.err.println("Contrasenya erronea!");
            System.out.println("Introdueixi la seva contrasenya");
            user = userController.login(username, scanner.next());
        }

        if (user == null) {
            System.err.println("Contrasenya erronea 3 cops");
        } else showPlayMenu(user);
    }

    private void register() {
        System.out.println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        String username =  scanner.next();
        String password1, password2;
        do {
            System.out.println("Introdueixi la seva contrasenya");
            password1 = scanner.next();
            System.out.println("Repeteixi la seva contrasenya");
            password2 = scanner.next();
        } while (!password1.equals(password2));

        User user = new User(username, password1);
        if (userController.insert(user)) {
            showPlayMenu(user);
        } else {
            System.err.println("Ja existeix l'usuari");
        }
    }
}
