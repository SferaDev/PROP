package presentation.visual.utils;

import java.lang.reflect.Field;

public class ColorManager {
    private static final String WHITE = "#ECEFF1";
    private static final String RED = "#F44336";
    private static final String RED_DARK = "#B71C1C";
    private static final String PURPLE = "#9C27B0";
    private static final String PURPLE_DARK = "#673AB7";
    private static final String BLUE = "#2196F3";
    private static final String BLUE_DARK = "#0D47A1";
    private static final String GREEN = "#4CAF50";
    private static final String GREEN_DARK = "#1B5E20";
    private static final String YELLOW = "#FFEB3B";
    private static final String YELLOW_DARK = "#FFC107";
    private static final String ORANGE = "#FF9800";
    private static final String ORANGE_DARK ="#FF5722";
    private static final String BROWN = "#795548";
    private static final String BROWN_DARK = "#3E2723";
    private static final String LIME = "#CDDC39";
    private static final String LIME_DARK = "#827717";
    private static final String TEAL = "#009688";
    private static final String TEAL_DARK = "#004D40";

    private static final String[] colorPalette = {
            BLUE, YELLOW, RED, PURPLE, GREEN, ORANGE, BROWN, LIME, TEAL
    };

    private static final String[] darkPalette = {
            BLUE_DARK, YELLOW_DARK, RED_DARK, PURPLE_DARK, GREEN_DARK, ORANGE_DARK, BROWN_DARK, LIME_DARK, TEAL_DARK
    };

    public static String getColor(String name) {
        try {
            Field field = ColorManager.class.getDeclaredField(name);
            field.setAccessible(true);
            return (String) field.get(String.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return WHITE;
        }
    }

    public static String getColor(int color) {
        return colorPalette[color%colorPalette.length];
    }

    public static String getDarkColor(int color) {
        return darkPalette[color%colorPalette.length];
    }
}
