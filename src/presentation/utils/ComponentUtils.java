package presentation.utils;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import presentation.controller.LocaleController;

public class ComponentUtils {

    public static void showInformationDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LocaleController.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showWarningDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(LocaleController.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showErrorDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(LocaleController.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showCustomDialog(String header, Node item) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LocaleController.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setGraphic(item);
        alert.showAndWait();
    }

    public static void buildScene(Class context, Stage stage, Parent parent, double minWidth, double minHeight) {
        stage.setTitle(LocaleController.getInstance().getString("APP_TITLE"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setWidth(minWidth);
        stage.setHeight(minHeight);
        stage.getIcons().add(new Image(context.getResource("/resources/img/ic_launcher.png").toExternalForm()));
        stage.show();
    }

    public static ColumnConstraints createColumnConstraint(int percent) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(percent);
        columnConstraints.setHgrow(Priority.SOMETIMES);
        return columnConstraints;
    }

    public static RowConstraints createRowConstraint(int percent) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(percent);
        rowConstraints.setVgrow(Priority.SOMETIMES);
        return rowConstraints;
    }
}
