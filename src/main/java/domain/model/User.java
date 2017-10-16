package domain.model;

import domain.model.player.UserPlayer;

import java.util.ArrayList;

public class User {
    // Player name can't be changed, it's user ID
    private String playerName;
    // Player password is private by design and has no getters or setters
    private String playerPassword;

    public User(String name, String password) {
        playerName = name;
        playerPassword = password;
    }

    public String getName() {
        return playerName;
    }

    public boolean testPassword(String passwordAttempt) {
        return playerPassword.equals(passwordAttempt);
    }

}
