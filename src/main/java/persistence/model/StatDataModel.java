package persistence.model;

import domain.controller.data.StatDataController;
import domain.model.Game;
import domain.model.Stat;
import persistence.DataModel;

import java.util.Map;

public class StatDataModel<E extends Stat> extends DataModel<E> implements StatDataController<E> {
    private static StatDataModel mInstance = new StatDataModel();

    private StatDataModel() {
        super("data/stats/");
    }

    public static StatDataModel getInstance() {
        return mInstance;
    }

    public String Ranking(){
        String ranking = readDisk("data/stats/");
        return ranking;
        //Holi Alexis, se que esto así no funciona pero has dicho que te pase lo que tengo

    }

    public void update(Map<Game,Integer> ranking) {

    }
}
