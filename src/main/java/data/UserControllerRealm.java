package data;

import domain.controller.UserController;
import domain.model.User;

import java.util.Set;

public class UserControllerRealm implements UserController {
    private static UserControllerRealm mInstance = new UserControllerRealm();

    public static UserControllerRealm getInstance() {
        return mInstance;
    }

    private UserControllerRealm() {
        // Should never be instantiated
    }

    @Override
    public User getUser(String name) {
        return null;
    }

    @Override
    public boolean exists(String name) {
        return false;
    }

    @Override
    public Set<User> all() {
        return null;
    }
}
