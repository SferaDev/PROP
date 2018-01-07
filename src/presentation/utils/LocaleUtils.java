package presentation.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Locale Utils
 *
 * @author Alexis Rico Carreto
 */
public class LocaleUtils {
    private static final LocaleUtils mInstance = new LocaleUtils();
    private Language mLanguage;

    private LocaleUtils() {
        // Empty constructor
    }

    /**
     * Gets the instance of the LocaleUtils.
     *
     * @return the instance of the LocaleUtils.
     */
    public static LocaleUtils getInstance() {
        return mInstance;
    }

    /**
     * Gets the string in the MessagesBundle from a given key
     *
     * @param key is the identifier of the string
     * @return the string
     */
    public String getString(String key) {
        return ResourceBundle.getBundle("resources/strings/MessagesBundle",
                getLanguage().toLocale()).getString(key);
    }

    /**
     * Gets the language of the user
     *
     * @return returns a language
     */
    public Language getLanguage() {
        // If language is not set, default to ENGLISH
        return mLanguage != null ? mLanguage : Language.ENGLISH;
    }

    /**
     * Sets a neew language
     *
     * @param locale is the neew language
     */
    public void setLanguage(Language locale) {
        mLanguage = locale;
    }

    public enum Language {
        SPANISH(new Locale("es", "ES")),
        ENGLISH(new Locale("en", "US")),
        CATALAN(new Locale("ca", "ES"));

        private final Locale locale;

        Language(Locale locale) {
            this.locale = locale;
        }

        Locale toLocale() {
            return locale;
        }
    }
}
