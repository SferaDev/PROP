package domain.model;

/**
 * The type User.
 */
public class User implements java.io.Serializable {
    private String playerName;
    private String playerPassword;

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param password the password
     */
    public User(String name, String password) {
        playerName = name;
        playerPassword = password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return playerName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return playerPassword;
    }

}
