package data.model.data;

import com.google.gson.Gson;
import data.utils.FileUtils;
import domain.controller.data.DataController;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class DataModel implements DataController {
    private Map<String, String> mData = new HashMap<>();

    private Gson gson = new Gson();

    DataModel() {
        ArrayList<String> files = FileUtils.listFiles(getFolderPath());
        for (String file : files) {
            mData.put(file, null);
        }
    }

    protected abstract String getFolderPath();

    @Override
    public boolean exists(String key) {
        return mData.containsKey(key);
    }

    @Override
    public boolean insert(Object item) {
        String itemTitle = item.toString();
        if (exists(itemTitle)) return false;

        String itemJson = gson.toJson(item);
        mData.put(itemTitle, itemJson);
        FileUtils.createFile(getFolderPath() + itemTitle);
        FileUtils.writeToFile(getFolderPath() + itemTitle, itemJson);
        return true;
    }

    @Override
    public void remove(Object item) {
        String itemTitle = item.toString();
        if (!exists(itemTitle)) return;
        mData.remove(itemTitle);
        FileUtils.deleteFile(getFolderPath() + itemTitle);
    }

    @Override
    public Object get(String key, Class type) {
        if (!exists(key)) return null;
        String json = mData.get(key);
        if (json == null) {
            json = readDisk(getFolderPath() + key);
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
