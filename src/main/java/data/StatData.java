package data;

public class StatData extends JSONController {
    private static StatData mInstance = new StatData();

    public static StatData getInstance() {
        return mInstance;
    }

    private StatData() {
        super();
    }

    @Override
    protected String getFolderPath() {
        return "data/stats/";
    }
}
