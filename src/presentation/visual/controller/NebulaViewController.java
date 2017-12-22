package presentation.visual.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NebulaViewController implements Initializable {
    @FXML
    private VBox navDrawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpDrawer();
    }

    private void setUpDrawer() {
        // Add listeners
    }
}