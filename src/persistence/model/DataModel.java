package persistence.model;

import domain.controller.data.DataController;
import persistence.utils.FileUtils;

import java.io.*;
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
    private final Map<String, E> mData = new HashMap<>();

    private final String mPath;

    /**
     * Instantiates a new Data model
     *
     * @param path the path
     */
    DataModel(String path) {
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
        mData.put(key, item);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(mPath + key), false));
            oos.writeObject(item);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        E object = mData.get(key);
        if (object == null) {
            object = readDisk(mPath + key);
            mData.replace(key, object);
        }
        return object;
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

    private E readDisk(String path) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            return (E) ois.readObject();
        } catch (FileNotFoundException e) {
            FileUtils.createFile(path);
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
