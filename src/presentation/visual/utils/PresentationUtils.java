package presentation.visual.utils;

import javafx.scene.control.Alert;

public class PresentationUtils {
    public static void showErrorDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mastermind"); // TODO: Strings
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
