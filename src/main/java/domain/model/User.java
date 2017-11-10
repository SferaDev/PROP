package domain.model;

public class User implements java.io.Serializable {
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

}
