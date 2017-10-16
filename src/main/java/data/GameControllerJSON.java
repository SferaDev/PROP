package data;

import com.google.gson.Gson;
import domain.controller.StatsController;

public class GameControllerJSON implements StatsController {
    private static final String FOLDER_LOCATION = "data/games/";

    //private Map<String, User> mGames;

    private Gson gson = new Gson();

    private static GameControllerJSON mInstance = new GameControllerJSON();

    public static GameControllerJSON getInstance() {
        return mInstance;
    }

    private GameControllerJSON() {
        // TODO: Load mGames
    }
}
