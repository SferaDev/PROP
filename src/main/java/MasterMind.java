import domain.controller.MainController;
import domain.controller.UserController;
import domain.model.User;
import domain.model.io.InputOutput;

import java.util.Scanner;

public class MasterMind {
    private MainController mainController;
    private InputOutput gameInterface;

    public static void main(String[] args) {
        MasterMind application = new MasterMind();
        // TODO: Args should define if terminal mode or gfx mode
        application.startApplication(true);
    }

    private void startApplication(boolean terminalMode) {
        mainController = MainController.getInstance();
        gameInterface = mainController.getGameInterface();

        System.out.println("Mastermind Menu");
        System.out.println("1. Regristrar-se");
        System.out.println("2. Identificar-se");
        System.out.println("3. Veure rankings");
        System.out.println("4. Ajuda");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        switch (sc.nextInt()) {
            case 1:
                login();
                break;
            case 2:
                identify();
                break;
            default:
                break;
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueixi el seu nom d'usuari");
        String username =  sc.next();

        UserController userController = mainController.getUserController();
        User user = userController.getUser(username);

        if (user == null) {
            gameInterface.outputError("Usuari no registrat");
        }

        System.out.println("Introdueixi la seva contrasenya");
        String password =  sc.next();
        boolean correctp = user.login(password);
        while (!correctp) {
            correctp = user.login(password);
            System.out.println("Contrasenya incorrecta, si us plau torni a escriure-la");
            password =  sc.next();
        }

    }

    private void identify() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Crei el seu nom d'usuari");
        String username =  sc.next();
        System.out.println("Crei la seva contrasenya");
        boolean incorrectp = true;
        String password =  sc.next();
        String password2;

        while (incorrectp){
            System.out.println("Repeteixi la seva contrasenya");
            password2 = sc.next();

            if (!password.equals(password2)) {
                System.out.println("Les contrasenyes no coincideixen, si us plau torni a introduir la seva contrasenya");
                password =  sc.next();
            }
            else incorrectp = false;
        }
        User user = new User(username, password);
    }

}
