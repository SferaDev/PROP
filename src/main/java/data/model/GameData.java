package data.model;

import data.JSONData;

public class GameData extends JSONData {
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
