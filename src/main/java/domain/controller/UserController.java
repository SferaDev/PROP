package domain.controller;

import domain.model.User;

import java.util.Set;

public interface UserController<User> extends DataController<User> {
    User login(String name, String pass);
}
