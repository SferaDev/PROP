package domain.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import domain.controller.data.StatDataController;
import persistence.model.StatDataModel;

public class Stat {
    private static Map<Game,Integer> ranking;
    private static Stat instance = new Stat();

    private StatDataController statController = StatDataModel.getInstance();

    private Stat() {
        ranking = new HashMap<>();
    }

    static Stat getInstance() {
        return instance;
    }

    private static void sortByValue(Map<Game, Integer> map) {
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


   void addScore(Game game, int score) {
       ranking.put(game, score);
       sortByValue(ranking);
       //statController.update(ranking); //esto no lo he acabado de hacer, mi idea era actualizar en la capa de datos

   }


}


