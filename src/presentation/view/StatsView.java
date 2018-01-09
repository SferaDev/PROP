package presentation.view;

import domain.controller.DomainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.utils.LocaleUtils;
import presentation.utils.TimeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * The Stats View
 *
 * @author Oriol Borrell Roig
 */
public class StatsView extends GridPane {

    private static final String Column1MapKey = "Column1";
    private static final String Column2MapKey = "Column2";

    private TableView tableViewPoints = new TableView<>();
    private TableView tableViewTime = new TableView<>();

    /**
     * Loads the StatsView to nebula.
     */
    public StatsView() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));

        BorderPane borderPanePoints = createBorderPane(LocaleUtils.getInstance().getString("POINT_STATS"),
                LocaleUtils.getInstance().getString("USERNAME"), LocaleUtils.getInstance().getString("POINTS"), tableViewPoints);
        BorderPane borderPaneTime = createBorderPane(LocaleUtils.getInstance().getString("TIME_STATS"),
                LocaleUtils.getInstance().getString("USERNAME"), LocaleUtils.getInstance().getString("TIME"), tableViewTime);

        borderPanePoints.prefWidthProperty().bind(widthProperty().divide(2.25));
        borderPaneTime.prefWidthProperty().bind(widthProperty().divide(2.25));

        add(borderPanePoints, 0, 0);
        add(borderPaneTime, 1, 0);

        reset();
    }

    private BorderPane createBorderPane(String title, String column1, String column2, TableView table) {
        BorderPane borderPane = new BorderPane();

        Label headTitle = new Label(title);
        headTitle.setTextFill(Color.WHITE);
        headTitle.setFont(Font.font(30));

        BorderPane.setAlignment(headTitle, Pos.CENTER);
        BorderPane.setAlignment(table, Pos.CENTER);

        BorderPane.setMargin(headTitle, new Insets(10));
        BorderPane.setMargin(table, new Insets(10));

        borderPane.setTop(headTitle);

        TableColumn<Map, String> firstDataColumn = new TableColumn<>(column1);
        TableColumn<Map, String> secondDataColumn = new TableColumn<>(column2);

        firstDataColumn.setCellValueFactory(new MapValueFactory(Column1MapKey));
        secondDataColumn.setCellValueFactory(new MapValueFactory(Column2MapKey));

        firstDataColumn.prefWidthProperty().bind(table.widthProperty().divide(2));
        secondDataColumn.prefWidthProperty().bind(table.widthProperty().divide(2));

        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getColumns().setAll(firstDataColumn, secondDataColumn);
        borderPane.setCenter(table);
        return borderPane;
    }

    /**
     * Resets the view, in order to actualize it
     */
    public void reset() {
        tableViewPoints.getItems().clear();
        tableViewTime.getItems().clear();
        tableViewPoints.getItems().addAll(generatePointDataInMap());
        tableViewTime.getItems().addAll(generateTimeDataInMap());
    }

    private ObservableList<Map> generatePointDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        Map<String, Long> pointRanking = DomainController.getInstance().getStatController().getPointRanking();

        for (Map.Entry<String, Long> entry : pointRanking.entrySet()) {
            Map<String, Object> dataRow1 = new HashMap<>();

            dataRow1.put(Column1MapKey, entry.getKey());
            dataRow1.put(Column2MapKey, entry.getValue());
            allData.addAll(dataRow1);
        }
        return allData;
    }

    private ObservableList<Map> generateTimeDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        Map<String, Long> TimeRanking = DomainController.getInstance().getStatController().getTimeRanking();

        for (Map.Entry<String, Long> entry : TimeRanking.entrySet()) {
            Map<String, String> dataRow = new HashMap<>();

            long time = entry.getValue();

            if (time > 0) {
                String value1 = entry.getKey().split("-")[0];
                String value2 = TimeUtils.timestampToString(time);

                dataRow.put(Column1MapKey, value1);
                dataRow.put(Column2MapKey, value2);
                allData.add(dataRow);
            }
        }
        return allData;
    }
}
