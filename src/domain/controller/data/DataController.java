package domain.controller.data;

import java.util.ArrayList;

/**
 * The interface Data controller
 *
 * @param <E> the type parameter
 * @author Alexis Rico Carreto
 */
public interface DataController<E> {
    /**
     * Exists boolean
     *
     * @param key the key
     * @return true if the key exists, false otherwise
     */
    boolean exists(String key);

    /**
     * Insert the item with its key in Data Model
     *
     * @param key  the key
     * @param item the item
     */
    void insert(String key, E item);

    /**
     * Replace the item with the key key by the new item
     *
     * @param key  the key
     * @param item the new item
     */
    void replace(String key, E item);

    /**
     * Remove the item with key key
     *
     * @param key the key
     */
    void remove(String key);

    /**
     * Get e
     *
     * @param key the key
     * @return the e
     */
    E get(String key);

    /**
     * Gets all keys
     *
     * @return all Keys
     */
    ArrayList allKeys();
}
