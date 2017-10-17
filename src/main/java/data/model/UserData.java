package data.model;

import data.JSONController;
import domain.controller.UserController;
import domain.model.User;

public class UserData extends JSONController implements UserController {
    private static UserData mInstance = new UserData();

    private UserData() {
        super();
    }

    public static UserData getInstance() {
        return mInstance;
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
