package persistence.model;

import domain.model.Game;
import persistence.DataModel;

/**
 * The type Game data model.
 *
 * @param <E> the type parameter
 * @author Elena Alonso Gonzalez
 */
public class GameDataModel<E extends Game> extends DataModel<E> {
    private static final GameDataModel mInstance = new GameDataModel();

    private GameDataModel() {
        super("data/games/");
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static GameDataModel getInstance() {
        return mInstance;
    }
}
