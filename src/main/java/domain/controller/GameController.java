package domain.controller;

import domain.model.User;

import java.util.Set;

public interface GameController {
    User getGame(String name);
    boolean exists(String name);
    Set<User> all();
}
