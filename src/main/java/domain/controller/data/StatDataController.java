package domain.controller.data;

import domain.model.Stat;

public interface StatDataController<E extends Stat> extends DataController<E> {
    String Ranking();
}


