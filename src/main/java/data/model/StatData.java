package data.model;

import data.JSONController;

public class StatData extends JSONController {
    private static StatData mInstance = new StatData();

    private StatData() {
        super();
    }

    public static StatData getInstance() {
        return mInstance;
    }

    @Override
    protected String getFolderPath() {
        return "data/stats/";
    }
}
