package domain.controller.data;

import domain.model.User;

/**
 * The interface User data controller.
 *
 * @param <E> the type parameter
 */
public interface UserDataController<E extends User> extends DataController<E> {
    /**
     * Login boolean.
     *
     * @param name the name
     * @param pass the pass
     * @return the boolean
     */
    boolean login(String name, String pass);
}
