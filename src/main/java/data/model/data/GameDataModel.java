package data.model.data;

public class GameDataModel extends DataModel {
    private static GameDataModel mInstance = new GameDataModel();

    private GameDataModel() {
        super();
    }

    public static GameDataModel getInstance() {
        return mInstance;
    }

    @Override
    protected String getFolderPath() {
        return "data/games/";
    }
}
