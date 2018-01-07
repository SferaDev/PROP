package presentation.utils;

/**
 * The Time Utils
 *
 * @author Oriol Borrell Roig
 */
public class TimeUtils {
    /**
     * Output Timestamp.
     *
     * @param time the time
     * @return the string
     */
    public static String timestampToString(long time) {
        long elapsed = time / 1000;
        int hours = (int) (elapsed / (3600));
        int minutes = (int) ((elapsed - (hours * 3600)) / 60);
        int seconds = (int) (elapsed - (hours * 3600) - minutes * 60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
