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
     * Create an user if it doesn't exist
     *
     * @param username the new username
     * @param password the new password
     * @param language the new language
     * @throws UserAlreadyExistsException the user already exists exception
     */
    public void createUser(String username, String password, String language) throws UserAlreadyExistsException {
        if (userDataController.exists(username)) throw new UserAlreadyExistsException();
        userDataController.insert(username, new User(username, password, language));
    }

    /**
     * Checks if the user can be logged in
     *
     * @param userName the user name
     * @param password the password
     * @return true if the user can be logged, false otherwise
     * @throws UserNotFoundException the user not found exception
     */
    public boolean loginUser(String userName, String password) throws UserNotFoundException {
        return userDataController.login(userName, password);
    }

    /**
     * Checks if the user already exists
     *
     * @param userName the user name to be checked
     * @return true if the user name have already exists, false otherwise
     */
    public boolean existsUser(String userName) {
        return userDataController.exists(userName);
    }

    /**
     * Delete user from the dataSet
     *
     * @param username the username
     */
    public void deleteUser(String username) {
        userDataController.remove(username);
    }

    /**
     * Change password of a certain user in the dataSet
     *
     * @param username the username
     * @param password the password
     */
    public void changePassword(String username, String password) {
        userDataController.replace(username, new User(username, password, getUserLanguage(username)));
    }

    /**
     * Return the language of a given user
     *
     * @param username the username
     * @return the language
     */
    public String getUserLanguage(String username) {
        User user = (User) userDataController.get(username);
        return user.getLanguage();
    }

    /**
     * Change language of a certain user in the dataSet
     *
     * @param username    the username
     * @param newLanguage the new language
     */
    public void changeLanguage(String username, String newLanguage) {
        User user = (User) userDataController.get(username);
        userDataController.replace(username, new User(username, user.getPassword(), newLanguage));
    }
}
