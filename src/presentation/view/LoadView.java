package presentation.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.controller.PresentationController;
import presentation.utils.ComponentUtils;
import presentation.view.components.RaisedButton;

import java.util.ArrayList;

public class LoadView extends GridPane {
    private final JFXListView<Label> gameListView = new JFXListView<>();

    private Integer pegs = 3;
    private Integer colors = 3;

    public LoadView() {
        setVgap(10);
        VBox newGameBox = new VBox();
        VBox savedGameBox = new VBox();

        RaisedButton newGameButton = new RaisedButton("Start Game"); // TODO: Strings
        RaisedButton savedGameButton = new RaisedButton("Start Game"); // TODO: Strings

        populateNewGameBox(newGameBox, newGameButton);
        populateSavedGameBox(savedGameBox, savedGameButton);

        getColumnConstraints().addAll(ComponentUtils.createColumnConstraint(50), ComponentUtils.createColumnConstraint(50));
        getRowConstraints().addAll(ComponentUtils.createRowConstraint(100));

        add(createBorderPane("New Game", newGameBox, newGameButton), 0, 0); // TODO: Strings
        add(createBorderPane("Saved Games", savedGameBox, savedGameButton), 1, 0); // TODO: Strings

        reset();
    }

    private void populateSavedGameBox(VBox savedGameBox, RaisedButton button) {
        gameListView.prefHeightProperty().bind(savedGameBox.heightProperty());
        gameListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        button.setOnAction(event -> {
            String game = gameListView.getSelectionModel().getSelectedItem().getText();
            PresentationController.getInstance().requestStartSavedGame(game);
        });
        savedGameBox.getChildren().add(gameListView);
        savedGameBox.setAlignment(Pos.CENTER);
        VBox.setMargin(gameListView, new Insets(15));
    }

    private void populateNewGameBox(VBox newGameBox, RaisedButton button) {
        HBox settingsBox = new HBox();
        settingsBox.setAlignment(Pos.CENTER);
        settingsBox.setSpacing(15);

        Label labelPegs = createLabel("Pegs:");
        labelPegs.setFont(Font.font(18));
        settingsBox.getChildren().add(labelPegs);

        JFXComboBox<Integer> pegsComboBox = new JFXComboBox<>();
        pegsComboBox.setStyle("-fx-background-color: #cfd8dc;");
        fillComboBox(pegsComboBox, 3, 9);
        pegsComboBox.getSelectionModel().selectFirst();
        pegsComboBox.setOnAction(event -> pegs = pegsComboBox.getSelectionModel().getSelectedItem());
        settingsBox.getChildren().add(pegsComboBox);

        Label labelColors = createLabel("Colors:");
        labelColors.setFont(Font.font(18));
        settingsBox.getChildren().add(labelColors);

        JFXComboBox<Integer> colorComboBox = new JFXComboBox<>();
        colorComboBox.setStyle("-fx-background-color: #cfd8dc;");
        fillComboBox(colorComboBox, 3, 9);
        colorComboBox.getSelectionModel().selectFirst();
        colorComboBox.setOnAction(event -> colors = colorComboBox.getSelectionModel().getSelectedItem());
        settingsBox.getChildren().add(colorComboBox);

        ToggleGroup roleGroup = new ToggleGroup();
        ToggleGroup algorithmGroup = new ToggleGroup();

        JFXRadioButton breakerRole = createRadioButton("Breaker");
        JFXRadioButton makerRole = createRadioButton("Maker");

        breakerRole.setToggleGroup(roleGroup);
        makerRole.setToggleGroup(roleGroup);
        breakerRole.setSelected(true);

        JFXRadioButton geneticAlgorithm = createRadioButton("Genetic");
        JFXRadioButton fiveGuessAlgorithm = createRadioButton("FiveGuess");

        geneticAlgorithm.setToggleGroup(algorithmGroup);
        fiveGuessAlgorithm.setToggleGroup(algorithmGroup);

        VBox roleBox = new VBox();
        roleBox.setSpacing(25);
        roleBox.getChildren().addAll(breakerRole, makerRole);

        VBox algorithmBox = new VBox();
        algorithmBox.setSpacing(25);
        algorithmBox.getChildren().addAll(geneticAlgorithm, fiveGuessAlgorithm);

        Label roleLabel = createLabel("Role"); // TODO: Strings
        Label algorithmLabel = createLabel("Algorithm"); // TODO: Strings
        Label settingsLabel = createLabel("Settings"); // TODO: Strings

        roleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            algorithmBox.setManaged(newValue.getUserData().equals("Maker"));
            algorithmBox.setVisible(newValue.getUserData().equals("Maker"));
            algorithmLabel.setManaged(newValue.getUserData().equals("Maker"));
            algorithmLabel.setVisible(newValue.getUserData().equals("Maker"));

            if (newValue.getUserData().equals("Breaker") && colorComboBox.getItems().size() == 4) {
                fillComboBox(pegsComboBox, 3, 9);
                fillComboBox(colorComboBox, 3, 9);

                geneticAlgorithm.setSelected(true);
            }

            algorithmGroup.selectedToggleProperty().addListener((observable1, oldValue1, newValue1) -> {
                if (newValue1.getUserData().equals("FiveGuess") && colorComboBox.getItems().size() == 7) {
                    fillComboBox(pegsComboBox, 3, 6);
                    fillComboBox(colorComboBox, 3, 6);
                } else if (newValue1.getUserData().equals("Genetic") && colorComboBox.getItems().size() == 4) {
                    fillComboBox(pegsComboBox, 3, 9);
                    fillComboBox(colorComboBox, 3, 9);
                }
            });
        });

        breakerRole.setSelected(true);
        geneticAlgorithm.setSelected(true);

        algorithmBox.setManaged(false);
        algorithmBox.setVisible(false);
        algorithmLabel.setManaged(false);
        algorithmLabel.setVisible(false);

        newGameBox.setSpacing(20);
        newGameBox.getChildren().addAll(roleLabel, roleBox, algorithmLabel, algorithmBox, settingsLabel, settingsBox);

        button.setOnAction(event -> {
            String computerName = algorithmGroup.getSelectedToggle().getUserData().toString();
            String role = roleGroup.getSelectedToggle().getUserData().toString();
            PresentationController.getInstance().requestStartGame(computerName, role, pegs, colors); // TODO
        });
    }

    @SuppressWarnings("SameParameterValue")
    private void fillComboBox(JFXComboBox<Integer> comboBox, int start, int end) {
        comboBox.getItems().clear();
        for (int i = start; i <= end; ++i) comboBox.getItems().add(i);
        comboBox.getSelectionModel().clearAndSelect(0);
    }

    private JFXRadioButton createRadioButton(String title) {
        JFXRadioButton result = new JFXRadioButton(title);
        result.setUserData(title);
        result.setTextFill(Color.WHITE);
        result.setFont(Font.font(18));
        return result;
    }

    private Label createLabel(String title) {
        Label result = new Label(title);
        result.setFont(Font.font(25));
        result.setTextFill(Color.WHITE);
        VBox.setMargin(result, new Insets(10));
        return result;
    }

    private BorderPane createBorderPane(String title, VBox mainContent, RaisedButton button) {
        BorderPane result = new BorderPane();
        Label titleLabel = createLabel(title);
        BorderPane.setMargin(titleLabel, new Insets(30, 30, 0, 30));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        result.setTop(titleLabel);
        BorderPane.setMargin(mainContent, new Insets(0, 30, 0, 30));
        mainContent.setSpacing(10);
        mainContent.setAlignment(Pos.TOP_CENTER);
        result.setCenter(mainContent);
        BorderPane.setMargin(button, new Insets(0, 30, 20, 30));
        BorderPane.setAlignment(button, Pos.CENTER);
        result.setBottom(button);
        return result;
    }

    public void reset() {
        // TODO: Reset focus on ToggleGroups
        gameListView.getItems().clear();
        ArrayList savedGames = PresentationController.getInstance().requestSavedGames();
        for (Object game : savedGames) {
            gameListView.getItems().add(new Label((String) game));
        }
    }
}
