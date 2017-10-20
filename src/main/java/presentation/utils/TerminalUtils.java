package presentation.utils;

public class TerminalUtils {
    public static void println(String string) {
        System.out.println(string);
        System.out.flush();
    }

    public static void errorln(String string) {
        System.err.println(string);
        System.err.flush();
    }
}
