public class Constants {

    static final int MAIN_REGISTER = 1;
    static final int MAIN_LOGIN = 2;
    static final int MAIN_STATS = 3;
    static final int MAIN_HELP = 4;
    static final int MAIN_EXIT = 5;

    static final String MAIN_MENU_TITLE = "  " + "Mastermind: Main Menu" + "  ";
    static final String MAIN_MENU_SEPARATOR = new String(new char[MAIN_MENU_TITLE.length()]).replace('\0', '*');
    static final String MAIN_REGISTER_TITLE = "Registrar-se";
    static final String MAIN_LOGIN_TITLE = "Identificar-se";
    static final String MAIN_STATS_TITLE = "Veure estadístiques";
    static final String MAIN_HELP_TITLE = "Ajuda";
    static final String MAIN_EXIT_TITLE = "Surt";

    static final int PLAY_NEW_GAME = 1;
    static final int PLAY_PREV_GAME = 2;
    static final int PLAY_STATS = 3;
    static final int PLAY_HELP = 4;
    static final int PLAY_BACK = 5;

    static final String PLAY_MENU_TITLE = "  " + "Mastermind: Play Menu" + "  ";
    static final String PLAY_MENU_SEPARATOR = new String(new char[PLAY_MENU_TITLE.length()]).replace('\0', '*');
    static final String PLAY_NEW_GAME_TITLE = "Nova partida";
    static final String PLAY_PREV_GAME_TITLE = "Continuar partida";
    static final String PLAY_STATS_TITLE = MAIN_STATS_TITLE;
    static final String PLAY_HELP_TITLE = MAIN_HELP_TITLE;
    static final String PLAY_BACK_TITLE = MAIN_EXIT_TITLE;

    static final String ERROR_USER_NOT_FOUND = "Usuari no registrat";
}
