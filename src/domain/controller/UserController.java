package domain.controller;

import domain.controller.data.UserDataController;
import domain.model.User;
import persistence.model.UserDataModel;

/**
 * The type User controller
 */
public class UserController {
    private static final UserController mInstance = new UserController();
    private final UserDataController userDataController = UserDataModel.getInstance();

    private UserController() {
        // Empty Constructor
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static UserController getInstance() {
        return mInstance;
    }

    /**
     * Create an user if he did not exist
     *
     * @param username the new username
     * @param password the new password
     * @return false if the user could not be created, true otherwise
     */
    public boolean createUser(String username, String password) {
        // TODO: Use exceptions!!
        if (userDataController.exists(username)) return false;
        userDataController.insert(username, new User(username, password));
        return true;
    }

    /**
     * Checks if the user parameters to be logged are correct
     *
     * @param userName the user name
     * @param password the password
     * @return true if the user can be logged, false otherwise
     */
    public boolean loginUser(String userName, String password) {
        // TODO: Use exceptions!!
        return userDataController.login(userName, password);
    }

    /**
     * Checks if the user name have already exists
     *
     * @param userName the user name to be checked
     * @return true if the user name have already exists, false otherwise
     */
    public boolean existsUser(String userName) {
        return userDataController.exists(userName);
    }
}
