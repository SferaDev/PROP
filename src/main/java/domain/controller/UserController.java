package domain.controller;

import domain.model.User;

import java.util.Set;

public interface UserController {
    User getUser(String name);
    boolean exists(String name);
    boolean insert(User user);
}
