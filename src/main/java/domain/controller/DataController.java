package domain.controller;

import com.afollestad.ason.Ason;

public interface DataController<E> {
    boolean exists(String key);

    boolean insert(E item);

    void remove(E item);

    E get(String key, Class cls);
}
