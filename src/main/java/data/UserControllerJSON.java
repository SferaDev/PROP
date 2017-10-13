package data;

import domain.controller.UserController;
import domain.model.User;

import java.util.Set;

public class UserControllerJSON implements UserController {
    private static UserControllerJSON mInstance = new UserControllerJSON();

    public static UserControllerJSON getInstance() {
        return mInstance;
    }

    private UserControllerJSON() {
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
