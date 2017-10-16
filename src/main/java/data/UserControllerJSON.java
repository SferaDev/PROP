package data;

import com.google.gson.Gson;
import domain.controller.UserController;
import domain.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UserControllerJSON implements UserController {
    private static final String FILE_LOCATION = "data/users.json";

    private Map<String, User> mUsers;

    private Gson gson = new Gson();

    private static UserControllerJSON mInstance = new UserControllerJSON();

    public static UserControllerJSON getInstance() {
        return mInstance;
    }

    private UserControllerJSON() {
        mUsers = openFile();
    }

    @Override
    public User login(String name, String pass) {
        if (exists(name)) {
            User user = mUsers.get(name);
            if (user.getPassword().equals(pass)) return user;
        }
        return null;
    }

    @Override
    public boolean exists(String name) {
        return mUsers.containsKey(name);
    }

    @Override
    public boolean insert(User user) {
        if (exists(user.getName())) return false;
        mUsers.put(user.getName(), user);
        updateDisk();
        return true;
    }

    private Map<String, User> openFile() {
        try {
            return gson.fromJson(new Scanner(new File(FILE_LOCATION)).useDelimiter("\\Z").next(),
                    (Type) new HashMap<>());
        } catch (FileNotFoundException e) {
            Path pathToFile = Paths.get(FILE_LOCATION);
            try {
                Files.createDirectories(pathToFile.getParent());
                Files.createFile(pathToFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return new HashMap<>();
        }
    }

    private void updateDisk() {
        try {
            FileWriter writer = new FileWriter(new File(FILE_LOCATION), false);
            writer.write(gson.toJson(mUsers));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
