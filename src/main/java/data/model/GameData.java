package data.model;

import data.JSONController;

public class GameData extends JSONController {
    private static GameData mInstance = new GameData();

    private GameData() {
        super();
    }

    public static GameData getInstance() {
        return mInstance;
    }

    @Override
    protected String getFolderPath() {
        return "data/games/";
    }
}
