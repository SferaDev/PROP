package presentation.utils;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ComponentUtils {
    public static void showErrorDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(LocaleUtils.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void buildScene(Class context, Stage stage, Parent parent, double minWidth, double minHeight) {
        stage.setTitle(LocaleUtils.getInstance().getString("APP_TITLE"));
        stage.setScene(new Scene(parent));
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.getIcons().add(new Image(context.getResource("/resources/img/ic_launcher.png").toExternalForm()));
        stage.show();
    }
}
