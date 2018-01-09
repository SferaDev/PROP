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

/**
 * The Component Utils
 *
 * @author Oriol Borrell Roig
 */
public class ComponentUtils {

    /**
     * Shows a information type alert.
     *
     * @param header  will be the header of the alert.
     * @param content will be the message of the alert.
     */
    public static void showInformationDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LocaleUtils.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Shows a warning type alert.
     *
     * @param header  will be the header of the alert.
     * @param content will be the message of the alert.
     */
    public static void showWarningDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(LocaleUtils.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Shows a error type alert.
     *
     * @param header  will be the header of the alert.
     * @param content will be the message of the alert.
     */
    public static void showErrorDialog(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(LocaleUtils.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Shows a custom type alert.
     *
     * @param header will be the header of the alert.
     * @param item   will be the custom graphic of the alert.
     */
    public static void showCustomDialog(String header, Node item) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LocaleUtils.getInstance().getString("APP_TITLE"));
        alert.setHeaderText(header);
        alert.setGraphic(item);
        alert.showAndWait();
    }

    /**
     * Builds a scene with the given parameters.
     *
     * @param context   the context to get resources
     * @param stage     the stage of the scene.
     * @param parent    the parent of the scene.
     * @param minWidth  the minimum width of the scene
     * @param minHeight the minimum height of the scene
     */
    public static void buildScene(Class context, Stage stage, Parent parent, double minWidth, double minHeight) {
        stage.setTitle(LocaleUtils.getInstance().getString("APP_TITLE"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setWidth(minWidth);
        stage.setHeight(minHeight);
        stage.getIcons().add(new Image(context.getResource("/resources/img/ic_launcher.png").toExternalForm()));
        stage.show();
    }

    /**
     * Creates a ColumnConstraints with the given width percentage.
     *
     * @param percent the width percentage.
     * @return the ColumnConstraints
     */
    public static ColumnConstraints createColumnConstraint(int percent) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(percent);
        columnConstraints.setHgrow(Priority.SOMETIMES);
        return columnConstraints;
    }

    /**
     * Creates a RowConstraints with the given height percentage.
     *
     * @param percent the height percentage.
     * @return the RowConstraints.
     */
    public static RowConstraints createRowConstraint(int percent) {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(percent);
        rowConstraints.setVgrow(Priority.SOMETIMES);
        return rowConstraints;
    }
}
