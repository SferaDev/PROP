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
import presentation.utils.LocaleUtils;
import presentation.view.components.RaisedButton;

import java.util.ArrayList;

public class LoadView extends GridPane {
    private final JFXListView<Label> gameListView = new JFXListView<>();
    private final ToggleGroup roleGroup = new ToggleGroup();
    private final ToggleGroup algorithmGroup = new ToggleGroup();

    private Integer pegs = 3;
    private Integer colors = 3;

    /**
     * Loads the LoadView tho nebula, where it will let the user configure the game.
     */
    public LoadView() {
        setVgap(10);
        VBox newGameBox = new VBox();
        VBox savedGameBox = new VBox();

        RaisedButton newGameButton = new RaisedButton(LocaleUtils.getInstance().getString("START_GAME"));
        RaisedButton savedGameButton = new RaisedButton(LocaleUtils.getInstance().getString("START_GAME"));

        populateNewGameBox(newGameBox, newGameButton);
        populateSavedGameBox(savedGameBox, savedGameButton);

        getColumnConstraints().addAll(ComponentUtils.createColumnConstraint(50), ComponentUtils.createColumnConstraint(50));
        getRowConstraints().addAll(ComponentUtils.createRowConstraint(100));

        add(createBorderPane(LocaleUtils.getInstance().getString("NEW_GAME"), newGameBox, newGameButton), 0, 0);
        add(createBorderPane(LocaleUtils.getInstance().getString("SAVED_GAMES"), savedGameBox, savedGameButton), 1, 0);
        reset();
    }

    private void populateSavedGameBox(VBox savedGameBox, RaisedButton button) {
        gameListView.prefHeightProperty().bind(savedGameBox.heightProperty());
        gameListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        button.setOnAction(event -> {
            String game = (String) gameListView.getSelectionModel().getSelectedItem().getUserData();
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

        Label labelPegs = createLabel(LocaleUtils.getInstance().getString("PEGS") + ": ");
        labelPegs.setFont(Font.font(18));
        settingsBox.getChildren().add(labelPegs);

        JFXComboBox<Integer> pegsComboBox = new JFXComboBox<>();
        pegsComboBox.setPrefWidth(2);
        pegsComboBox.setStyle("-fx-background-color: #cfd8dc;");
        fillComboBox(pegsComboBox, 3, 9);
        pegsComboBox.getSelectionModel().selectFirst();
        pegsComboBox.setOnAction(event -> pegs = pegsComboBox.getSelectionModel().getSelectedItem());
        settingsBox.getChildren().add(pegsComboBox);

        Label labelColors = createLabel(LocaleUtils.getInstance().getString("COLORS") + ": ");
        labelColors.setFont(Font.font(18));
        settingsBox.getChildren().add(labelColors);

        JFXComboBox<Integer> colorComboBox = new JFXComboBox<>();
        colorComboBox.setPrefWidth(2);
        colorComboBox.setStyle("-fx-background-color: #cfd8dc;");
        fillComboBox(colorComboBox, 3, 9);
        colorComboBox.getSelectionModel().selectFirst();
        colorComboBox.setOnAction(event -> colors = colorComboBox.getSelectionModel().getSelectedItem());
        settingsBox.getChildren().add(colorComboBox);

        JFXRadioButton breakerRole = createRadioButton("Breaker");
        JFXRadioButton makerRole = createRadioButton("Maker");

        breakerRole.setToggleGroup(roleGroup);
        makerRole.setToggleGroup(roleGroup);

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

        Label roleLabel = createLabel(LocaleUtils.getInstance().getString("ROLE"));
        Label algorithmLabel = createLabel(LocaleUtils.getInstance().getString("ALGORITHM"));
        Label settingsLabel = createLabel(LocaleUtils.getInstance().getString("SETTINGS"));

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

    /**
     * Resets the view, in order to actualize it
     */
    public void reset() {
        algorithmGroup.selectToggle(algorithmGroup.getToggles().get(0));
        roleGroup.selectToggle(roleGroup.getToggles().get(0));

        gameListView.getItems().clear();
        ArrayList savedGames = PresentationController.getInstance().requestSavedGames();
        for (Object game : savedGames) {
            String[] title = ((String) game).split("-");
            String name = title[1] + (title[1].equals("Maker") ? " vs " + title[2] : "") +
                    " " + title[3] + " Pegs " + title[4] + " Colors (" + title[5].replace("_", ":") + ")";
            Label label = new Label(name);
            label.setUserData(game);
            gameListView.getItems().add(label);
        }
    }
}
