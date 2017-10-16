import domain.controller.MainController;
import domain.controller.UserController;
import domain.model.User;
import domain.model.io.InputOutput;

import java.util.Scanner;

public class MasterMind {
    private MainController mainController;
    private UserController userController;
    private InputOutput gameInterface;

    public static void main(String[] args) {
        MasterMind application = new MasterMind();
        // TODO: Args should define if terminal mode or gfx mode
        application.startApplication(true);
    }

    private void startApplication(boolean terminalMode) {
        mainController = MainController.getInstance();
        userController = mainController.getUserController();
        gameInterface = mainController.getGameInterface();

        if (terminalMode) {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.println(Constants.MAIN_MENU_TITLE);
                System.out.println(Constants.REGISTER_MENU + ". " + Constants.REGISTER_MENU_TITLE);
                System.out.println(Constants.LOGIN_MENU    + ". " + Constants.LOGIN_MENU_TITLE);
                System.out.println(Constants.STATS_MENU    + ". " + Constants.STATS_MENU_TITLE);
                System.out.println(Constants.HELP_MENU     + ". " + Constants.HELP_MENU_TITLE);

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
                    default:
                        System.err.println();
                        break;
                }

            } while (scanner.hasNext());
        } else {
            // GFX Mode
        }
    }

    private void login() {
        System.out.println("Introdueixi el seu nom d'usuari");
        Scanner scanner = new Scanner(System.in);
        User user = userController.getUser(scanner.next());

        if (user == null) {
            System.err.println(Constants.ERROR_USER_NOT_FOUND);
            return;
        }

        System.out.println("Introdueixi la seva contrasenya");
        String password = scanner.next();
        while (!user.testPassword(password)) {
            System.err.println("Contrasenya incorrecta, si us plau torni a escriure-la");
            password = scanner.next();
        }

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
    }

}
