package domain.controller;

import domain.controller.data.DataController;
import persistence.model.StatDataModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatController {
    private static StatController mInstance = new StatController();
    private static HashMap<String, Long> pointRanking;
    private static HashMap<String, Long> timeRanking;
    private DataController statDataController = StatDataModel.getInstance();

    private StatController() {
        pointRanking = (HashMap<String, Long>) statDataController.get("pointRanking");
        timeRanking = (HashMap<String, Long>) statDataController.get("timeRanking");
    }

    public static StatController getInstance() {
        return mInstance;
    }

    private static void sortByValue(HashMap<String, Long> map) {
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


    public void addScore(String userName, String gameTitle, int points, long time) {
        pointRanking.replace(userName, pointRanking.containsKey(userName) ?
                pointRanking.get(userName) + points : points);
        timeRanking.put(gameTitle, time);

        sortByValue(pointRanking);
        sortByValue(timeRanking);

        statDataController.replace("pointRanking", pointRanking);
        statDataController.replace("timeRanking", timeRanking);
    }
}


