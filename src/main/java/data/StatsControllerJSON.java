package data;

import com.google.gson.Gson;
import domain.controller.StatsController;
import domain.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StatsControllerJSON implements StatsController {
    private static final String FILE_LOCATION = "data/stats.json";

    //private Map<String, User> mStats;

    private Gson gson = new Gson();

    private static StatsControllerJSON mInstance = new StatsControllerJSON();

    public static StatsControllerJSON getInstance() {
        return mInstance;
    }

    private StatsControllerJSON() {
        // TODO: Load mStats
    }
}
