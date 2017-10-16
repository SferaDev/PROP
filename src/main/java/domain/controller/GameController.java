package domain.controller;

import domain.model.Game;
import domain.model.User;

import java.util.Set;

public interface GameController {
    Game getGame(String name);
    boolean exists(String name);
    Set<User> all();
}
