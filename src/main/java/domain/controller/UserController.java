package domain.controller;

import domain.controller.data.UserDataController;
import domain.model.User;
import persistence.model.UserDataModel;

public class UserController {
    private static UserController mInstance = new UserController();
    private UserDataController userDataController = UserDataModel.getInstance();

    private UserController() {
        // Empty Constructor
    }

    public static UserController getInstance() {
        return mInstance;
    }

    public boolean createUser(String username, String password) {
        // TODO: Use exceptions!!
        if (userDataController.exists(username)) return false;
        userDataController.insert(username, new User(username, password));
        return true;
    }

    public boolean loginUser(String userName, String password) {
        // TODO: Use exceptions!!
        return userDataController.login(userName, password);
    }

    public boolean existsUser(String userName) {
        return userDataController.exists(userName);
    }
}
