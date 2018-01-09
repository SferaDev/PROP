package persistence.model;

import domain.controller.data.UserDataController;
import domain.model.User;
import domain.model.exceptions.UserNotFoundException;

/**
 * The type User data model
 *
 * @param <E> the type parameter
 * @author Elena Alonso Gonzalez
 */
public class UserDataModel<E extends User> extends DataModel<E> implements UserDataController<E> {
    private static final UserDataModel mInstance = new UserDataModel();

    private UserDataModel() {
        super("data/users/");
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static UserDataModel getInstance() {
        return mInstance;
    }

    /**
     * Checks if the user exists, and if it exists, checks if the password is correct
     *
     * @param name is the name of the user user entered (can be not correct)
     * @param pass is the password that the user entered (can be not correct)
     * @return if exists a username with the password given.
     * @throws UserNotFoundException in case that the username does not exist.
     */
    @Override
    public boolean login(String name, String pass) throws UserNotFoundException {
        if (!exists(name)) throw new UserNotFoundException();
        User user = get(name);
        return user.getPassword().equals(pass);
    }
}
