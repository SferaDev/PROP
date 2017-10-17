package data.model;

import data.JSONController;
import domain.controller.UserController;
import domain.model.User;

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
