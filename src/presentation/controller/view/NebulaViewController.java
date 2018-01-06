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
import javafx.stage.Stage;
import presentation.controller.LocaleController;
import presentation.controller.PresentationController;
import presentation.view.*;
import presentation.view.components.ColorRow;
import presentation.view.components.ControlRow;

import java.net.URL;
import java.util.ResourceBundle;

public class NebulaViewController implements Initializable {
    private final LoadView loadView = new LoadView();
    private final StatsView statsView = new StatsView();
    private final UserView userView = new UserView();
    private final HelpView helpView = new HelpView();
    @FXML
    private VBox navDrawer;
    @FXML
    private BorderPane mainContent;
    private BoardPane boardPane;
    private GameView currentGameView;
    private boolean isPlaying = false;

    /**
     * Inicialize the nebula view controller
     * @param location is the url where to find the Nebula.fxml
     * @param resources is the recoure bundle
     */
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
                else mainContent.setCenter(loadView);
                break;
            case "STATS":
                statsView.reset();
                mainContent.setCenter(statsView);
                break;
            case "USER":
                mainContent.setCenter(userView);
                break;
            case "HELP":
                mainContent.setCenter(helpView);
                break;
            case "LOG_OUT":
                Stage stage = (Stage) mainContent.getScene().getWindow();
                stage.close();
                PresentationController.getInstance().launchLoginForm(stage);
                break;
        }
    }

    /**
     * Starts a game with the given parameters
     * @param role is the role of the user.
     * @param computer is the computer who you want to play against
     * @param pegs number of pegs of the game
     * @param colors number of colors of the game
     */
    public void startGame(String role, String computer, int pegs, int colors) {
        isPlaying = true;
        boardPane = new BoardPane();
        currentGameView = new GameView(boardPane, role, computer, pegs, colors);
        mainContent.setCenter(currentGameView);
    }

    /**
     * Enda a game
     */
    public void finishGame() {
        isPlaying = false;
        loadView.reset();
        mainContent.setCenter(loadView);
    }

    /**
     * Adds the control of the last turn
     * @param blacks is the number of black pegs of the control row
     * @param whites is the number of whites pegs of the control row
     */
    public void addControlLastTurn(int blacks, int whites) {
        boardPane.addControlRow(new ControlRow(blacks, whites));
    }

    /**
     * Adds a color row to the board pane.
     * @param colorRow is the color row that will be added.
     */
    public void addTurn(ColorRow colorRow) {
        boardPane.addColorRow(colorRow);
    }

    /**
     * Adds a action pane to the GameView
     * @param pane the Node that will be added
     */
    public void addActionPane(Node pane) {
        currentGameView.addActionPane(pane);
    }

    /**
     * Removes a action pane from the GameView
     */
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

    /**
     * Adds the correct row to the GameView
     * @param row is the row that will be added
     */
    public void addCorrectRow(ColorRow row) {
        currentGameView.addCorrectRow(row);
    }
}