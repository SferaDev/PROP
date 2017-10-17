package data;

import com.google.gson.Gson;
import data.utils.FileUtils;
import domain.controller.DataController;
import domain.model.Game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class JSONController implements DataController {
    protected Map<String, Object> mData = new HashMap<>();

    private Gson gson = new Gson();

    protected JSONController() {
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
        if (exists(item.toString())) return false;
        mData.put(item.toString(), item);
        FileUtils.createFile(getFolderPath() + item.toString());
        FileUtils.writeToFile(getFolderPath() + item.toString(), gson.toJson(item));
        return true;
    }

    @Override
    public void remove(Object item) {
        if (!exists(item.toString())) return;
        mData.remove(item);
        FileUtils.deleteFile(getFolderPath() + item.toString());
    }

    @Override
    public Object get(String key) {
        if (!exists(key)) return null;
        Object object = mData.get(key);
        if (object == null) {
            object = readDisk(getFolderPath() + key);
            mData.replace(key, object);
        }
        return object;
    }

    private Game readDisk(String path) {
        try {
            return gson.fromJson(FileUtils.readFromFile(path), Game.class);
        } catch (FileNotFoundException e) {
            FileUtils.createFile(path);
            return null;
        }
    }
}
