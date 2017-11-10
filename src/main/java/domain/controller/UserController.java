package domain.controller;

import domain.controller.data.UserDataController;
import domain.model.User;
import persistence.model.UserDataModel;

/**
 * The type User controller.
 */
public class UserController {
    private static UserController mInstance = new UserController();
    private UserDataController userDataController = UserDataModel.getInstance();

    private UserController() {
        // Empty Constructor
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserController getInstance() {
        return mInstance;
    }

    /**
     * Create user boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean createUser(String username, String password) {
        // TODO: Use exceptions!!
        if (userDataController.exists(username)) return false;
        userDataController.insert(username, new User(username, password));
        return true;
    }

    /**
     * Login user boolean.
     *
     * @param userName the user name
     * @param password the password
     * @return the boolean
     */
    public boolean loginUser(String userName, String password) {
        // TODO: Use exceptions!!
        return userDataController.login(userName, password);
    }

    /**
     * Exists user boolean.
     *
     * @param userName the user name
     * @return the boolean
     */
    public boolean existsUser(String userName) {
        return userDataController.exists(userName);
    }
}
