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
     * Check if key value exists in the dataSet
     *
     * @param key the key
     * @return true if the key exists, false otherwise
     */
    boolean exists(String key);

    /**
     * Insert the item with its key in the dataSet
     *
     * @param key  the key
     * @param item the item
     */
    void insert(String key, E item);

    /**
     * Replace the item with the key by the new item in the dataSet
     *
     * @param key  the key
     * @param item the new item
     */
    void replace(String key, E item);

    /**
     * Remove the item with the key in the dataSet
     *
     * @param key the key
     */
    void remove(String key);

    /**
     * Get E element with the key in the dataSet
     *
     * @param key the key
     * @return the e
     */
    E get(String key);

    /**
     * Gets all keys from the dataSet
     *
     * @return all Keys array list
     */
    ArrayList allKeys();
}
