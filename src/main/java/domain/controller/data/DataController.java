package domain.controller.data;

public interface DataController<E> {
    boolean exists(String key);

    void insert(String key, E item);

    void replace(String key, E item);

    void remove(String key);

    E get(String key);
}
