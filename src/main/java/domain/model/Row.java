package domain.model;

import domain.model.peg.Peg;

import java.util.ArrayList;
import java.util.Comparator;

public class Row<E extends Peg> extends ArrayList<E> {
    public void order() {
        sort(new Comparator<E>() {
            @Override
            public int compare(E e1, E e2) {

                return 0;
            }
        });
    }
}
