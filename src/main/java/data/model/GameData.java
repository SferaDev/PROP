package data.model;

import data.JSONController;

public class GameData extends JSONController {
    private static GameData mInstance = new GameData();

    public static GameData getInstance() {
        return mInstance;
    }

    private GameData() {
        super();
    }

    @Override
    protected String getFolderPath() {
        return "data/games/";
    }
}
