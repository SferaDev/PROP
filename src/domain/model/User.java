package domain.model;

/**
 * The type User.
 *
 * @author Elena Alonso Gonzalez
 */
public class

User implements java.io.Serializable {
    private final String playerName;
    private final String playerPassword;
    private final String playerLanguage;

    /**
     * Instantiates a new User
     *
     * @param name     the user name
     * @param password the user password
     * @param language the user language
     */
    public User(String name, String password, String language) {
        playerName = name;
        playerPassword = password;
        playerLanguage = language;
    }

    /**
     * Gets name
     *
     * @return the name
     */
    public String getName() {
        return playerName;
    }

    /**
     * Gets user password
     *
     * @return the password
     */
    public String getPassword() {
        return playerPassword;
    }

    /**
     * Gets user language
     *
     * @return the password
     */
    public String getLanguage() {
        return playerLanguage;
    }
}
