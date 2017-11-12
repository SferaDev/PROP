package domain.controller.data;

import java.util.ArrayList;

/**
 * The interface Data controller.
 *
 * @param <E> the type parameter
 */
public interface DataController<E> {
    /**
     * Exists boolean.
     *
     * @param key the key
     * @return the boolean
     */
    boolean exists(String key);

    /**
     * Insert.
     *
     * @param key  the key
     * @param item the item
     */
    void insert(String key, E item);

    /**
     * Replace.
     *
     * @param key  the key
     * @param item the item
     */
    void replace(String key, E item);

    /**
     * Remove.
     *
     * @param key the key
     */
    void remove(String key);

    /**
     * Get e.
     *
     * @param key the key
     * @return the e
     */
    E get(String key);

    /**
     * All keys.
     *
     * @return all Keys
     */
    ArrayList allKeys();
}
