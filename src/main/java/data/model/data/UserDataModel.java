package data.model.data;

import domain.controller.data.UserController;
import domain.model.User;

public class UserDataModel extends DataModel implements UserController {
    private static UserDataModel mInstance = new UserDataModel();

    private UserDataModel() {
        super();
    }

    public static UserDataModel getInstance() {
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
