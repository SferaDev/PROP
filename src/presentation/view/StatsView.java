
package presentation.view;

import com.jfoenix.controls.JFXRadioButton;
import domain.controller.DomainController;
import domain.controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;
import presentation.controller.PresentationController;

import java.util.HashMap;
import java.util.Map;

public class StatsView extends VBox {

    public static final String Column1MapKey = "Username";
    public static final String Column2MapKey = "Points";

    public Label headTitle = new Label();

    public TableColumn<Map, String> firstDataColumn;
    public TableColumn<Map, String> secondDataColumn;

    public TableView table_view;


    public StatsView() {
        setSpacing(15);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));

        headTitle = new Label("Point Ranking");
        headTitle.setTextFill(Color.WHITE);
        headTitle.setFont(Font.font(30));

        HBox tableRadioGroupBox = new HBox();
        ToggleGroup statsGroup = new ToggleGroup();
        JFXRadioButton pointRadioButton = createRadioButton("Point Stats");
        JFXRadioButton timeRadioButton = createRadioButton("Time Stats");

        pointRadioButton.setToggleGroup(statsGroup);
        timeRadioButton.setToggleGroup(statsGroup);
        pointRadioButton.setSelected(true);

        pointRadioButton.setOnAction(event -> onClickPointRadioButton());
        timeRadioButton.setOnAction(event -> onClickTimeRadioButton());


        tableRadioGroupBox.setAlignment(Pos.CENTER);
        tableRadioGroupBox.getChildren().addAll(pointRadioButton, timeRadioButton);


        firstDataColumn = new TableColumn<>("Username");
        secondDataColumn = new TableColumn<>("Points");

        firstDataColumn.setCellValueFactory(new MapValueFactory(Column1MapKey));
        firstDataColumn.setMinWidth(200);
        secondDataColumn.setCellValueFactory(new MapValueFactory(Column2MapKey));
        secondDataColumn.setMinWidth(200);

        table_view = new TableView<>(generatePointDataInMap());
        table_view.setMaxWidth(400);

        //table_view.setEditable(true);
        table_view.getSelectionModel().setCellSelectionEnabled(true);
        table_view.getColumns().setAll(firstDataColumn, secondDataColumn);
        Callback<TableColumn<Map, String>, TableCell<Map, String>>
                cellFactoryForMap = new Callback<TableColumn<Map, String>,
                TableCell<Map, String>>() {
            @Override
            public TableCell call(TableColumn p) {
                return new TextFieldTableCell(new StringConverter() {
                    @Override
                    public String toString(Object t) {
                        return t.toString();
                    }

                    @Override
                    public Object fromString(String string) {
                        return string;
                    }
                });
            }
        };
        firstDataColumn.setCellFactory(cellFactoryForMap);
        secondDataColumn.setCellFactory(cellFactoryForMap);


        getChildren().addAll(headTitle, tableRadioGroupBox , table_view);

    }

    private void onClickPointRadioButton() {
        table_view.getItems().remove(0,table_view.getItems().size());
        headTitle.setText("Point Ranking");
        secondDataColumn.setText("Points");
        table_view.getItems().addAll(generatePointDataInMap());
    }

    private void onClickTimeRadioButton() {
        table_view.getItems().remove(0,table_view.getItems().size());
        headTitle.setText("Time Ranking");
        secondDataColumn.setText("Time");
        table_view.getItems().addAll(generateTimeDataInMap());
    }

    private ObservableList<Map> generatePointDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        Map<String, Long> pointRanking = DomainController.getInstance().getStatController().getPointRanking();

        for (Map.Entry entry : pointRanking.entrySet()) {
            Map<String, String> dataRow = new HashMap<>();

            String value1 = entry.getKey().toString();
            String value2 = entry.getValue().toString();
            dataRow.put(Column1MapKey, value1);
            dataRow.put(Column2MapKey, value2);
            allData.add(dataRow);
        }
        return allData;
    }

    private ObservableList<Map> generateTimeDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        Map<String, Long> TimeRanking = DomainController.getInstance().getStatController().getTimeRanking();

        for (Map.Entry entry : TimeRanking.entrySet()) {
            Map<String, String> dataRow = new HashMap<>();

            String value1 = entry.getKey().toString();
            String[] parts = value1.split("-");
            value1 = parts[0];

            String value2 = entry.getValue().toString();
            dataRow.put(Column1MapKey, value1);
            dataRow.put(Column2MapKey, value2);
            allData.add(dataRow);
        }
        return allData;
    }

    private JFXRadioButton createRadioButton(String title) {
        JFXRadioButton result = new JFXRadioButton(title);
        result.setUserData(title);
        result.setTextFill(Color.WHITE);
        result.setFont(Font.font(18));
        return result;
    }
}
