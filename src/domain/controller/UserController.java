package domain.controller;

import domain.controller.data.UserDataController;
import domain.model.User;
import domain.model.exceptions.UserAlreadyExistsException;
import domain.model.exceptions.UserNotFoundException;
import persistence.model.UserDataModel;

/**
 * The type User controller
 *
 * @author Elena Alonso Gonzalez
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
     */
    public void createUser(String username, String password) throws UserAlreadyExistsException {
        if (userDataController.exists(username)) throw new UserAlreadyExistsException();
        userDataController.insert(username, new User(username, password));
    }

    /**
     * Checks if the user parameters to be logged are correct
     *
     * @param userName the user name
     * @param password the password
     * @return true if the user can be logged, false otherwise
     */
    public boolean loginUser(String userName, String password) throws UserNotFoundException {
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
