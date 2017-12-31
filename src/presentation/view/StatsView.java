package presentation.view;

import domain.controller.DomainController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Map;

public class StatsView extends VBox {
    public StatsView() {
        setSpacing(15);
        setAlignment(Pos.CENTER);

        Label pointTitle = new Label("Point Ranking");
        pointTitle.setFont(Font.font(30));

        Map<String, Long> pointRanking = DomainController.getInstance().getStatController().getPointRanking(); // TODO: 3-layer

        GridPane gridPane = new GridPane();
        int i = 0;
        for (Map.Entry entry : pointRanking.entrySet()) {
            Label name = new Label(entry.getKey().toString());
            Label points = new Label(entry.getValue().toString());
            name.setFont(Font.font(18));
            points.setFont(Font.font(18));
            getChildren().addAll(name, points);
            i++;
        }
        getChildren().add(gridPane);
    }
}
