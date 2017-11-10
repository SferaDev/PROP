package presentation;

/**
 * The type Constants.
 */
public class Constants {
    /**
     * The Main register.
     */
    static final int MAIN_REGISTER = 1;
    /**
     * The Main login.
     */
    static final int MAIN_LOGIN = 2;
    /**
     * The Main stats.
     */
    static final int MAIN_STATS = 3;
    /**
     * The Main help.
     */
    static final int MAIN_HELP = 4;
    /**
     * The Main exit.
     */
    static final int MAIN_EXIT = 5;

    /**
     * The Main menu title.
     */
    static final String MAIN_MENU_TITLE = "  " + "Mastermind: Menu Principal" + "  ";
    /**
     * The Main menu separator.
     */
    static final String MAIN_MENU_SEPARATOR = new String(new char[MAIN_MENU_TITLE.length()]).replace('\0', '=');
    /**
     * The Main register title.
     */
    static final String MAIN_REGISTER_TITLE = "Registrar-se";
    /**
     * The Main login title.
     */
    static final String MAIN_LOGIN_TITLE = "Identificar-se";
    /**
     * The Main stats title.
     */
    static final String MAIN_STATS_TITLE = "Veure estadístiques";
    /**
     * The Main help title.
     */
    static final String MAIN_HELP_TITLE = "Ajuda";
    /**
     * The Main exit title.
     */
    static final String MAIN_EXIT_TITLE = "Surt";

    /**
     * The Play new game.
     */
    static final int PLAY_NEW_GAME = 1;
    /**
     * The Play prev game.
     */
    static final int PLAY_PREV_GAME = 2;
    /**
     * The Play stats.
     */
    static final int PLAY_STATS = 3;
    /**
     * The Play help.
     */
    static final int PLAY_HELP = 4;
    /**
     * The Play back.
     */
    static final int PLAY_BACK = 5;

    /**
     * The Play menu title.
     */
    static final String PLAY_MENU_TITLE = "  " + "Mastermind: Benvingut!" + "  ";
    /**
     * The Play menu separator.
     */
    static final String PLAY_MENU_SEPARATOR = new String(new char[PLAY_MENU_TITLE.length()]).replace('\0', '=');
    /**
     * The Play new game title.
     */
    static final String PLAY_NEW_GAME_TITLE = "Nova partida";
    /**
     * The Play prev game title.
     */
    static final String PLAY_PREV_GAME_TITLE = "Continuar partida";
    /**
     * The Play stats title.
     */
    static final String PLAY_STATS_TITLE = MAIN_STATS_TITLE;
    /**
     * The Play help title.
     */
    static final String PLAY_HELP_TITLE = MAIN_HELP_TITLE;
    /**
     * The Play back title.
     */
    static final String PLAY_BACK_TITLE = MAIN_EXIT_TITLE;

    /**
     * The New breaker game.
     */
    static final int NEW_BREAKER_GAME = 1;
    /**
     * The New maker game.
     */
    static final int NEW_MAKER_GAME = 2;
    /**
     * The New back.
     */
    static final int NEW_BACK = 3;

    /**
     * The New menu title.
     */
    static final String NEW_MENU_TITLE = "  " + "Mastermind: Nou joc" + "  ";
    /**
     * The New menu separator.
     */
    static final String NEW_MENU_SEPARATOR = new String(new char[NEW_MENU_TITLE.length()]).replace('\0', '=');
    /**
     * The New breaker game title.
     */
    static final String NEW_BREAKER_GAME_TITLE = "Breaker";
    /**
     * The New maker game title.
     */
    static final String NEW_MAKER_GAME_TITLE = "Maker";
    /**
     * The New back title.
     */
    static final String NEW_BACK_TITLE = MAIN_EXIT_TITLE;

    /**
     * The New fiveguess game.
     */
    static final int NEW_FIVEGUESS_GAME = 1;
    /**
     * The New genetic game.
     */
    static final int NEW_GENETIC_GAME = 2;
    /**
     * The New dummy game.
     */
    static final int NEW_DUMMY_GAME = 3;
    /**
     * The New back algorithm.
     */
    static final int NEW_BACK_ALGORITHM = 4;

    /**
     * The New fiveguess game title.
     */
    static final String NEW_FIVEGUESS_GAME_TITLE = "FiveGuessComputer";
    /**
     * The New genetic game title.
     */
    static final String NEW_GENETIC_GAME_TITLE = "GeneticComputer";
    /**
     * The New dummy game title.
     */
    static final String NEW_DUMMY_GAME_TITLE = "DummyComputer";
    /**
     * The New back algorithm title.
     */
    static final String NEW_BACK_ALGORITHM_TITLE = MAIN_EXIT_TITLE;

    /**
     * The Error user not found.
     */
    static final String ERROR_USER_NOT_FOUND = "Usuari no registrat";


}
