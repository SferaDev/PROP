package presentation.view;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.controller.PresentationController;

public class UserOptionsView extends VBox {
    //private PresentationController presentationController = PresentationController.getInstance();
    //private String username = PresentationController.getInstance().getNebulaController().getUsername();

    public UserOptionsView() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(50, 25, 25, 50));

        //About the Username...
        MyLabel textUsernameLable = new MyLabel("Username: ");
        grid.add(textUsernameLable, 0, 0);

        MyLabel textUsername = new MyLabel("p"); //TODO: Get real username, lines 18 and 19 gives an error
        grid.add(textUsername, 1, 0);

        //About the Password...
        MyLabel textPasswordLable = new MyLabel("Password: ");
        grid.add(textPasswordLable, 0, 1);

        JFXPasswordField oldPasswordField = new JFXPasswordField();
        oldPasswordField.setStyle("-fx-background-color: #cfd8dc;");
        oldPasswordField.setPromptText("Old password"); //TODO: Center the prompt text
        grid.add(oldPasswordField, 1, 1);

        JFXPasswordField newPasswordField = new JFXPasswordField();
        newPasswordField.setStyle("-fx-background-color: #cfd8dc;");
        newPasswordField.setPromptText("New password"); //TODO: Center the prompt text
        grid.add(newPasswordField, 1, 2);

        JFXButton changePasswordButton = new JFXButton("Change");
        changePasswordButton.setStyle("-fx-background-color: #eceff1;");
        grid.add(changePasswordButton, 3, 2);
        changePasswordButton.setOnMouseClicked(event -> changePassword(oldPasswordField.getText(), newPasswordField.getText()));

        //About the Language...
        MyLabel textLanguageLable = new MyLabel("Selected language: ");
        grid.add(textLanguageLable, 0, 3);


        JFXComboBox languageComboBox = new JFXComboBox();
        languageComboBox.setStyle("-fx-background-color: #cfd8dc;");
        languageComboBox.getItems().add("Català");
        languageComboBox.getItems().add("Español");
        languageComboBox.getItems().add("English");
        languageComboBox.setPromptText("Select language..");
        grid.add(languageComboBox, 1, 3);

        /*languageComboBox.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object==null? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });*/



        getChildren().addAll(grid);
    }

    private void changePassword(String oldPassword, String newPassword) {
        //String username = p; //TODO: Get real username
        //Boolean correctOldPassword = PresentationController.getInstance().requestLogin(username, oldPassword);
    }

    private class MyLabel extends javafx.scene.control.Label {
        private MyLabel(String text) {
            setAlignment(Pos.CENTER);
            setTextAlignment(TextAlignment.CENTER);
            setTextFill(Color.WHITE);
            setFont(Font.font(18));
            setText(text);
        }
    }

}
