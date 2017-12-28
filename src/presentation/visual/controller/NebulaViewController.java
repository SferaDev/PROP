package presentation.visual.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.visual.utils.ColorManager;
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

    private BoardPane boardPane;
    private boolean isPlaying = false;

    private GameView currentGameView;
    private NewGameView newGameView = new NewGameView();
    private StatsView statsView = new StatsView();
    private UserOptionsView userOptionsView = new UserOptionsView();
    private HelpView helpView = new HelpView();

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
                if (isPlaying) mainContent.setCenter(currentGameView);
                else mainContent.setCenter(newGameView);
                break;
            case "STATS":
                mainContent.setCenter(statsView);
                break;
            case "USER":
                mainContent.setCenter(userOptionsView);
                break;
            case "HELP":
                mainContent.setCenter(helpView);
                break;
            case "LOG_OUT":
                System.exit(0); // TODO: Should close and launch LoginView
                break;
        }
    }

    public void startGame(int size) {
        isPlaying = true;
        boardPane = new BoardPane(size);
        currentGameView = new GameView(boardPane);
        mainContent.setCenter(currentGameView);
    }

    public void finishGame() {
        isPlaying = false;
        mainContent.setCenter(newGameView);
    }

    public void addControlLastTurn(int blacks, int whites) {
        boardPane.addControlRow(new ControlRow(boardPane.getSize(), blacks, whites));
        currentGameView.scrollDown();
    }

    public void addTurn(ColorRow colorRow) {
        boardPane.addColorRow(colorRow);
        currentGameView.scrollDown();
    }

    public void addControlPane(ControlInput pane) {
        currentGameView.addControlPane(pane);
    }

    public void removeControlPane() {
        currentGameView.removeControlPane();
    }

    private JFXButton createDrawerButton(String textId) {
        JFXButton button = new JFXButton();
        button.setPrefWidth(100);
        button.setText(LocaleUtils.getInstance().getString(textId));
        button.setTextFill(Color.web(ColorManager.getColor("WHITE"))); // TODO: Colors
        button.setFont(new Font(20));
        button.setOnMouseClicked(event -> onNavigationDrawerClick(textId));
        return button;
    }
}