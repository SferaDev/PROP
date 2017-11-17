package persistence;

import domain.controller.data.DataController;
import persistence.utils.Base64Encoder;
import persistence.utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Data model
 *
 * @param <E> the type parameter
 * @author Alexis Rico Carreto
 */
public abstract class DataModel<E extends Serializable> implements DataController<E> {
    private final Map<String, String> mData = new HashMap<>();

    private final String mPath;

    /**
     * Instantiates a new Data model
     *
     * @param path the path
     */
    protected DataModel(String path) {
        mPath = path;

        ArrayList<String> files = FileUtils.listFiles(mPath);
        for (String file : files) {
            mData.put(file, null);
        }
    }

    /**
     * Checks if the key exists
     *
     * @param key the key
     * @return true if exists, false otherwise
     */
    @Override
    public boolean exists(String key) {
        return mData.containsKey(key);
    }


    /**
     * Inserts the key with the item
     *
     * @param key  the key
     * @param item the item
     */
    @Override
    public void insert(String key, E item) {
        if (exists(key)) return;

        String serialItem = Base64Encoder.toString(item);
        mData.put(key, serialItem);
        FileUtils.createFile(mPath + key);
        FileUtils.writeToFile(mPath + key, serialItem);
    }

    /**
     * Replaces the item by the new item with key key
     *
     * @param key  the key
     * @param item the new item
     */
    @Override
    public void replace(String key, E item) {
        remove(key);
        insert(key, item);
    }

    /**
     * Removes the item with the key key
     *
     * @param key the key
     */
    @Override
    public void remove(String key) {
        if (!exists(key)) return;
        mData.remove(key);
        FileUtils.deleteFile(mPath + key);
    }

    /**
     * Gets the item with the key key
     *
     * @param key the key
     * @return the given object
     */
    @Override
    public E get(String key) {
        if (!exists(key)) return null;
        String serialObject = mData.get(key);
        if (serialObject == null) {
            serialObject = readDisk(mPath + key);
            mData.replace(key, serialObject);
        }
        return (E) Base64Encoder.fromString(serialObject);
    }

    /**
     * Gets all keys
     *
     * @return all keys
     */
    @Override
    public ArrayList allKeys() {
        return new ArrayList<>(mData.keySet());
    }

    private String readDisk(String path) {
        try {
            return FileUtils.readFromFile(path);
        } catch (FileNotFoundException e) {
            FileUtils.createFile(path);
            return null;
        }
    }
}
