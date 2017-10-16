package data;

import com.google.gson.Gson;
import domain.controller.UserController;
import domain.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.*;

public class UserControllerJSON implements UserController {
    private static final String FILE_LOCATION = "data/users.json";
    private Map<String, User> mUsers;

    private static UserControllerJSON mInstance = new UserControllerJSON();

    public static UserControllerJSON getInstance() {
        return mInstance;
    }

    private UserControllerJSON() {
        mUsers = openFile();
    }

    @Override
    public User getUser(String name) {
        if (exists(name)) return mUsers.get(name);
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
        return true;
    }

    private Map<String, User> openFile() {
        try {
            Gson gson = new Gson();
            return gson.fromJson(new Scanner(new File(FILE_LOCATION)).useDelimiter("\\Z").next(),
                    (Type) new HashMap<>());
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        }
    }
}
