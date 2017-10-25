package data.model;

import data.JSONData;

public class StatData extends JSONData {
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
