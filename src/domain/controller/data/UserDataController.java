package domain.controller.data;

import domain.model.User;
import domain.model.exceptions.UserNotFoundException;

/**
 * The interface User data controller.
 *
 * @param <E> the type parameter
 * @author Alexis Rico Carreto
 */
public interface UserDataController<E extends User> extends DataController<E> {
    /**
     * Logs a new user into the system
     *
     * @param name the user name
     * @param pass the password
     * @return true if the user can be logged in, false otherwise
     * @throws UserNotFoundException if the user is not found
     */
    boolean login(String name, String pass) throws UserNotFoundException;
}
