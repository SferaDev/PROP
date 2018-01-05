package presentation.controller;

import javafx.scene.paint.Color;

import java.lang.reflect.Field;

public class ColorController {
    private static final String RED = "#F44336";
    private static final String RED_DARK = "#B71C1C";
    private static final String PURPLE = "#4A148C";
    private static final String PURPLE_DARK = "#9C27B0";
    private static final String BLUE = "#2196F3";
    private static final String BLUE_DARK = "#0D47A1";
    private static final String GREEN = "#4CAF50";
    private static final String GREEN_DARK = "#1B5E20";
    private static final String YELLOW = "#FFEB3B";
    private static final String YELLOW_DARK = "#FFC107";
    private static final String BROWN = "#795548";
    private static final String BROWN_DARK = "#3E2723";
    private static final String PINK = "#E91E63";
    private static final String PINK_DARK = "#880E4F";
    private static final String ORANGE = "#FF5722";
    private static final String ORANGE_DARK = "#BF360C";
    private static final String BLACK = "#212121";
    private static final String BLACK_DARK = "#000000";
    private static final String WHITE = "#FFFFFF";
    private static final String WHITE_DARK = "#ECEFF1";

    @SuppressWarnings("unused")
    private static final String MAIN_COLOR = "#37474F";

    private static final String[] colorPalette = {
            BLUE, YELLOW, RED, GREEN, PURPLE, PINK, BROWN, ORANGE, BLACK, WHITE
    };

    private static final String[] darkPalette = {
            BLUE_DARK, YELLOW_DARK, RED_DARK, GREEN_DARK, PURPLE_DARK, PINK_DARK, BROWN_DARK, ORANGE_DARK, BLACK_DARK, WHITE_DARK
    };

    public static String getColor(String name) {
        try {
            Field field = ColorController.class.getDeclaredField(name);
            field.setAccessible(true);
            return (String) field.get(String.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return WHITE;
        }
    }

    public static String getColor(int color) {
        return colorPalette[(color - 1) % colorPalette.length];
    }

    @SuppressWarnings("unused")
    public static String getDarkColor(int color) {
        return darkPalette[color % colorPalette.length];
    }

    public static String getContrast(int color) {
        if (color <= 0) return WHITE_DARK;
        Color paint = Color.web(colorPalette[(color - 1) % colorPalette.length]);
        double a = 1 - (0.299 * paint.getRed() + 0.587 * paint.getGreen() + 0.114 * paint.getBlue() / 255);
        if (a < 0.5) return BLACK;
        else return WHITE_DARK;
    }
}
