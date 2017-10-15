import domain.controller.MainController;
import domain.model.User;

import java.util.Scanner;

public class MasterMind {
    public static void mainmenu() {
        System.out.println("Benvolgut/da marqui una opció");
        System.out.println("1. Regristrar-se");
        System.out.println("2. Identificar-se");
        System.out.println("3. Veure rankings");
        System.out.println("4. Ajuda");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();

        if (op == 1) logIn();
        else if (op == 2) identify();
    }

    public static void logIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueixi el seu nom d'usuari");
        String username =  sc.next();

        MainController Mc = MainController.getInstance();
        domain.controller.UserController Uc = Mc.getUserController();
        User user  = Uc.getUser(username);

        System.out.println("Introdueixi la seva contrasenya1");
        String password =  sc.next();
        boolean correctp = user.login(password);
        while (!correctp) {
            correctp = user.login(password);
            System.out.println("Contrasenya incorrecta, si us plau torni a escriure-la");
            password =  sc.next();
        }

    }

    public static void identify() {
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

    public static void main (String[] args) {
        mainmenu();
    }

}
