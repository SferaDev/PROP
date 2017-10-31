package data;

import domain.model.Game;

public class GameDataModel<E extends Game> extends DataModel<E> {
    private static GameDataModel mInstance = new GameDataModel();

    private GameDataModel() {
        super("data/games/");
    }

    public static GameDataModel getInstance() {
        return mInstance;
    }
}
