package domain.model;

import domain.model.peg.Peg;

import java.util.ArrayList;

public class Row<E extends Peg> extends ArrayList<E> {
    public Row() {
        super();
    }

    public Row(int size) {
        super(size);
    }

    public Row(Row<E> original) {
        super(original);
    }
}
