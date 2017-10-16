package domain.controller;

import domain.model.User;

import java.util.Set;

public interface UserController {
    User login(String name, String pass);
    boolean exists(String name);
    boolean insert(User user);
}
