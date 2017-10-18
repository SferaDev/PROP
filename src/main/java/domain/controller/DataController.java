package domain.controller;

public interface DataController<E> {
    boolean exists(String key);

    boolean insert(E item);

    void remove(E item);

    E get(String key, Class cls);
}
