package data.model;

import data.JSONData;
import domain.controller.UserController;
import domain.model.User;

public class UserData extends JSONData implements UserController {
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
            User user = (User) get(name, User.class);
            if (user.getPassword().equals(pass)) return user;
        }
        return null;
    }
}
