package data.model.data;

public class StatDataModel extends DataModel {
    private static StatDataModel mInstance = new StatDataModel();

    private StatDataModel() {
        super();
    }

    public static StatDataModel getInstance() {
        return mInstance;
    }

    @Override
    protected String getFolderPath() {
        return "data/stats/";
    }
}
