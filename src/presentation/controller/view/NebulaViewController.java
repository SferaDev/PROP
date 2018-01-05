package presentation.controller.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.controller.LocaleController;
import presentation.view.*;
import presentation.view.components.ColorRow;
import presentation.view.components.ControlRow;

import java.net.URL;
import java.util.ResourceBundle;

public class NebulaViewController implements Initializable {
    private final LoadView newGameView = new LoadView();
    private final StatsView statsView = new StatsView();
    private final UserOptionsView userOptionsView = new UserOptionsView();
    private final HelpView helpView = new HelpView();
    @FXML
    private VBox navDrawer;
    @FXML
    private BorderPane mainContent;
    private BoardPane boardPane;
    private GameView currentGameView;
    private boolean isPlaying = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpDrawer();
        onNavigationDrawerClick("");
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
            default:
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

    public void startGame(String role, String computer, int pegs, int colors) {
        isPlaying = true;
        boardPane = new BoardPane();
        currentGameView = new GameView(boardPane, role, computer, pegs, colors);
        mainContent.setCenter(currentGameView);
    }

    public void finishGame() {
        isPlaying = false;
        mainContent.setCenter(newGameView);
    }

    public void addControlLastTurn(int blacks, int whites) {
        boardPane.addControlRow(new ControlRow(blacks, whites));
    }

    public void addTurn(ColorRow colorRow) {
        boardPane.addColorRow(colorRow);
    }

    public void addActionPane(Node pane) {
        currentGameView.addActionPane(pane);
    }

    public void removeActionPane() {
        currentGameView.removeActionPane();
        JFXSpinner spinner = new JFXSpinner();
        spinner.setRadius(25);
        currentGameView.addActionPane(spinner);
    }

    private JFXButton createDrawerButton(String textId) {
        JFXButton button = new JFXButton();
        button.setPrefWidth(100);
        button.setText(LocaleController.getInstance().getString(textId));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-radius: 0px");
        button.setFont(new Font(20));
        button.setOnAction(event -> onNavigationDrawerClick(textId));
        return button;
    }

    public void addCorrectRow(ColorRow row) {
        currentGameView.addCorrectRow(row);
    }
}