package persistence.model;

import domain.controller.StatController;
import persistence.DataModel;

import java.util.HashMap;

public class StatDataModel<E extends StatController> extends DataModel<HashMap> {
    private static StatDataModel mInstance = new StatDataModel();

    private StatDataModel() {
        super("data/stats/");
    }

    public static StatDataModel getInstance() {
        return mInstance;
    }
}
