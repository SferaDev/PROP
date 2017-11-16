package persistence.model;

import domain.controller.StatController;
import persistence.DataModel;

import java.util.HashMap;

/**
 * The type Stat data model.
 *
 * @param <E> the type parameter
 * @author Elena Alonso Gonzalez
 */
public class StatDataModel<E extends StatController> extends DataModel<HashMap> {
    private static final StatDataModel mInstance = new StatDataModel();

    private StatDataModel() {
        super("data/stats/");
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static StatDataModel getInstance() {
        return mInstance;
    }
}
