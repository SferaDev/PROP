package data;

import com.afollestad.ason.Ason;
import data.utils.FileUtils;
import domain.controller.DataController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class JSONData implements DataController {
    private Map<String, Ason> mData = new HashMap<>();

    protected JSONData() {
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
        Ason object = Ason.serialize(item);
        mData.put(item.toString(), object);
        FileUtils.createFile(getFolderPath() + item.toString());
        FileUtils.writeToFile(getFolderPath() + item.toString(), object.toString());
        return true;
    }

    @Override
    public void remove(Object item) {
        if (!exists(item.toString())) return;
        mData.remove(item);
        FileUtils.deleteFile(getFolderPath() + item.toString());
    }

    @Override
    public Object get(String key, Class cls) {
        return Ason.deserialize(getAson(key), cls);
    }

    private Ason getAson(String key) {
        if (!exists(key)) return null;
        Ason object = mData.get(key);
        if (object == null) {
            object = readDisk(getFolderPath() + key);
            mData.replace(key, object);
        }
        return object;
    }

    private Ason readDisk(String path) {
        try {
            return new Ason(FileUtils.readFromFile(path));
        } catch (FileNotFoundException e) {
            FileUtils.createFile(path);
            return null;
        }
    }
}
