package data.model.data;

import domain.model.Stat;

public class StatDataModel<E extends Stat> extends DataModel<E> {
    private static StatDataModel mInstance = new StatDataModel();

    private StatDataModel() {
        super("data/stats/");
    }

    public static StatDataModel getInstance() {
        return mInstance;
    }
}
