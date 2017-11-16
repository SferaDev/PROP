package persistence.model;

import domain.controller.data.UserDataController;
import domain.model.User;
import persistence.DataModel;

/**
 * The type User data model
 *
 * @param <E> the type parameter
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

    @Override
    public boolean login(String name, String pass) {
        if (exists(name)) {
            User user = get(name);
            if (user.getPassword().equals(pass)) return true;
        }
        return false;
    }
}
