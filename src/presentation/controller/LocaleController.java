package presentation.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleController {
    private static final LocaleController mInstance = new LocaleController();
    private Language mLanguage;

    private LocaleController() {
        mLanguage = PresentationController.getInstance().requestUserLanguage();
        if (mLanguage == null) mLanguage = Language.ENGLISH;
    }

    public static LocaleController getInstance() {
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
