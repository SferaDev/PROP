package persistence;

import com.google.gson.Gson;
import persistence.utils.FileUtils;
import domain.controller.data.DataController;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class DataModel<E> implements DataController<E> {
    private Map<String, String> mData = new HashMap<>();

    private String mPath;

    private Gson gson = new Gson();

    DataModel(String path) {
        mPath = path;

        ArrayList<String> files = FileUtils.listFiles(mPath);
        for (String file : files) {
            mData.put(file, null);
        }
    }

    @Override
    public boolean exists(String key) {
        return mData.containsKey(key);
    }

    @Override
    public boolean insert(E item) {
        String itemTitle = item.toString();
        if (exists(itemTitle)) return false;

        String itemJson = gson.toJson(item);
        mData.put(itemTitle, itemJson);
        FileUtils.createFile(mPath + itemTitle);
        FileUtils.writeToFile(mPath + itemTitle, itemJson);
        return true;
    }

    @Override
    public void remove(E item) {
        String itemTitle = item.toString();
        if (!exists(itemTitle)) return;
        mData.remove(itemTitle);
        FileUtils.deleteFile(mPath + itemTitle);
    }

    @Override
    public E get(String key, Class type) {
        if (!exists(key)) return null;
        String json = mData.get(key);
        if (json == null) {
            json = readDisk(mPath + key);
            mData.replace(key, json);
        }
        return gson.fromJson(json, (Type) type);
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
