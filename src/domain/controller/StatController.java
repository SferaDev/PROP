package domain.controller;

import domain.controller.data.DataController;
import persistence.model.StatDataModel;

import java.util.HashMap;

/**
 * The Stat controller
 *
 * @author Elena Alonso Gonzalez
 */
public class StatController {
    private static final StatController mInstance = new StatController();
    private static HashMap<String, Long> pointRanking;
    private static HashMap<String, Long> timeRanking;
    private final DataController statDataController = StatDataModel.getInstance();

    private StatController() {
        pointRanking = (HashMap<String, Long>) statDataController.get("pointRanking");
        timeRanking = (HashMap<String, Long>) statDataController.get("timeRanking");

        if (pointRanking == null) pointRanking = new HashMap<>();
        if (timeRanking == null) timeRanking = new HashMap<>();
    }

    /**
     * Gets instance
     *
     * @return the instance
     */
    public static StatController getInstance() {
        return mInstance;
    }

    /**
     * Add the score of the user
     *
     * @param userName  the user name playing as a Breaker
     * @param gameTitle the game title
     * @param points    the punctuation obtained int he game
     * @param time      the time spend in the game
     */
    public void addScore(String userName, String gameTitle, long points, long time) {
        if (pointRanking.containsKey(userName)) {
            pointRanking.replace(userName, pointRanking.get(userName) + points);
        } else {
            pointRanking.put(userName, points);
        }
        timeRanking.put(gameTitle, time);

        updateStats();
    }

    /**
     * Update the Stats on the Data Controller
     */
    private void updateStats() {
        statDataController.replace("pointRanking", pointRanking);
        statDataController.replace("timeRanking", timeRanking);
    }

    /**
     * Gets the point ranking
     *
     * @return the point ranking
     */
    public HashMap<String, Long> getPointRanking() {
        return pointRanking;
    }

    /**
     * Gets the time ranking
     *
     * @return the time ranking
     */
    public HashMap<String, Long> getTimeRanking() {
        return timeRanking;
    }
}


