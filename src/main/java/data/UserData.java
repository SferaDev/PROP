package data;

import com.google.gson.Gson;
import data.utils.FileUtils;
import domain.controller.DataController;
import domain.controller.UserController;
import domain.model.User;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.*;

public class UserData extends JSONController implements UserController {
    private static UserData mInstance = new UserData();

    public static UserData getInstance() {
        return mInstance;
    }

    private UserData() {
        super();
    }

    @Override
    protected String getFolderPath() {
        return "data/users/";
    }

    @Override
    public User login(String name, String pass) {
        if (exists(name)) {
            User user = (User) mData.get(name);
            if (user.getPassword().equals(pass)) return user;
        }
        return null;
    }
}
