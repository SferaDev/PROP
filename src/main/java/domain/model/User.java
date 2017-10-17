package domain.model;

import domain.model.player.UserPlayer;

import java.util.ArrayList;

public class User {
    private String playerName;
    private String playerPassword;

    public User(String name, String password) {
        playerName = name;
        playerPassword = password;
    }

    public String getName() {
        return playerName;
    }

    public String getPassword() {
        return playerPassword;
    }

    @Override
    public String toString() {
        return getName();
    }

}
