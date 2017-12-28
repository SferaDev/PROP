package presentation.visual.controller;

import com.jfoenix.controls.JFXButton;
import domain.controller.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.visual.utils.LocaleUtils;
import presentation.visual.view.*;
import presentation.visual.view.components.ColorRow;
import presentation.visual.view.components.ControlInput;
import presentation.visual.view.components.ControlRow;

import java.net.URL;
import java.util.ResourceBundle;

public class NebulaViewController implements Initializable {
    @FXML
    private VBox navDrawer;
    @FXML
    private BorderPane mainContent;

    private GameView gameView;
    private BoardPane boardPane;
    private boolean isPlaying = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpDrawer();
    }

    private void setUpDrawer() {
        navDrawer.getChildren().add(navDrawer.getChildren().size() - 1, createDrawerButton("PLAY"));
        navDrawer.getChildren().add(navDrawer.getChildren().size() - 1, createDrawerButton("STATS"));
        navDrawer.getChildren().add(navDrawer.getChildren().size() - 1, createDrawerButton("USER"));
        navDrawer.getChildren().add(navDrawer.getChildren().size(), createDrawerButton("HELP"));
        navDrawer.getChildren().add(navDrawer.getChildren().size(), createDrawerButton("LOG_OUT"));
    }

    private void onNavigationDrawerClick(String item) {
        switch (item) {
            case "PLAY":
                if (isPlaying) mainContent.setCenter(gameView);
                else mainContent.setCenter(new NewGameView());
                break;
            case "STATS":
                mainContent.setCenter(new StatsView());
                break;
            case "USER":
                mainContent.setCenter(new UserOptionsView());
                break;
            case "HELP":
                DomainController.getInstance().getGameController().startNewGame("alexis", "FiveGuessComputer", "MAKER", 5, 6, 12);
                mainContent.setCenter(new HelpView());
                break;
            case "LOG_OUT":
                System.exit(0); // TODO: Should close and launch LoginView
                break;
        }
    }

    public void startGame(int size) {
        isPlaying = true;
        boardPane = new BoardPane(size);
        gameView = new GameView(boardPane);
        mainContent.setCenter(gameView);
    }

    public void finishGame() {
        isPlaying = false;
    }

    public void addControlLastTurn(int blacks, int whites) {
        boardPane.addControlRow(new ControlRow(boardPane.getSize(), blacks, whites));
        gameView.scrollDown();
    }

    public void addTurn(ColorRow colorRow) {
        boardPane.addColorRow(colorRow);
        gameView.scrollDown();
    }

    public int requestControl(String title) {
        ControlInput controlInput = new ControlInput(title);
        gameView.addControlPane(controlInput);
        // TODO: If invalid show error and wait again NumberFormatException
        return Integer.parseInt(controlInput.getResult());
    }

    private JFXButton createDrawerButton(String textId) {
        JFXButton button = new JFXButton();
        button.setPrefWidth(100);
        button.setText(LocaleUtils.getInstance().getString(textId));
        button.setTextFill(Color.web("eceff1")); // TODO: Colors
        button.setFont(new Font(20));
        button.setOnMouseClicked(event -> onNavigationDrawerClick(textId));
        return button;
    }
}