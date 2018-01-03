package presentation.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleUtils {
    private static final LocaleUtils mInstance = new LocaleUtils();
    private Language mLanguage;

    private LocaleUtils() {
        // TODO: Load from disk last locale
        mLanguage = Language.ENGLISH;
    }

    public static LocaleUtils getInstance() {
        return mInstance;
    }

    public String getString(String key) {
        return ResourceBundle.getBundle("resources/strings/MessagesBundle",
                mLanguage.toLocale()).getString(key);
    }

    public Language getLanguage() {
        return mLanguage;
    }

    public void setLanguage(Language locale) {
        mLanguage = locale;
        // TODO: Store in disk current locale
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
