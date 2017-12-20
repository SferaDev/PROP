package presentation.visual.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleUtils {
    private static final LocaleUtils mInstance = new LocaleUtils();
    private Locale mLocale;

    private LocaleUtils() {
        // TODO: Load from disk last locale
        mLocale = new Locale("en", "US");
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static LocaleUtils getInstance() {
        return mInstance;
    }

    public void setLocale(Locale locale) {
        mLocale = locale;
        // TODO: Store in disk current locale
    }

    public String getString(String key) {
        return ResourceBundle.getBundle("MessagesBundle", mLocale).getString(key);
    }
}
