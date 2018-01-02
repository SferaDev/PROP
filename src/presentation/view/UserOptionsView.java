package presentation.view;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import domain.controller.UserController;
import domain.model.exceptions.UserNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.controller.PresentationController;
import presentation.utils.ComponentUtils;

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

        //String username = PresentationController.getInstance().getNebulaController().getUsername();
        MyLabel textUsername = new MyLabel("p"); //TODO: Get real username
        grid.add(textUsername, 1, 0);

        //About the Password...
        MyLabel textPasswordLable = new MyLabel("Password: ");
        grid.add(textPasswordLable, 0, 1);

        JFXPasswordField oldPasswordField = new JFXPasswordField();
        oldPasswordField.setStyle("-fx-background-radius: 0px");
        oldPasswordField.setStyle("-fx-background-color: #cfd8dc;");
        oldPasswordField.setPromptText("Old password");
        grid.add(oldPasswordField, 1, 1);

        JFXPasswordField newPasswordField = new JFXPasswordField();
        newPasswordField.setStyle("-fx-background-radius: 0px");
        newPasswordField.setStyle("-fx-background-color: #cfd8dc;");
        newPasswordField.setPromptText("New password");
        grid.add(newPasswordField, 1, 2);

        JFXButton changePasswordButton = new JFXButton("Change");
        changePasswordButton.setStyle("-fx-background-color: #eceff1;");
        grid.add(changePasswordButton, 3, 2);
        changePasswordButton.setOnMouseClicked(event -> changePasswordAndClear(oldPasswordField, newPasswordField));

        //About the Language...
        MyLabel textLanguageLable = new MyLabel("Selected language: ");
        grid.add(textLanguageLable, 0, 3);


        JFXComboBox<String> languageComboBox = new JFXComboBox();
        languageComboBox.setStyle("-fx-background-color: #cfd8dc;");
        languageComboBox.getItems().add("Català");
        languageComboBox.getItems().add("Español");
        languageComboBox.getItems().add("English");
        languageComboBox.getSelectionModel().clearAndSelect(2);//TODO: De moment per defecte agafa English
        languageComboBox.setOnAction(event -> handleComboBoxChange(languageComboBox.getSelectionModel().getSelectedItem()));
        grid.add(languageComboBox, 1, 3);
        getChildren().addAll(grid);
    }

    private void handleComboBoxChange(String string) {
        switch (string) {//TODO:
            case "Català":
                break;
            case "Español":
                break;
            case "English":
                break;
        }

    }

    private void changePasswordAndClear(JFXPasswordField oldPasswordField, JFXPasswordField newPasswordField) {
        String username = "p"; //TODO: Get real username
        Boolean correctOldPassword = false;
        try {
            correctOldPassword = PresentationController.getInstance().requestLogin(username, oldPasswordField.getText());
        } catch (UserNotFoundException e) {
            ComponentUtils.showErrorDialog("User not found", "Looks like the user is not in our database!"); //Mai hauriem d'arribar aqui.
        }
        if (correctOldPassword) {
            if (newPasswordField.getText().equals(oldPasswordField.getText())){
                ComponentUtils.showWarningDialog("Password Issue", "The new password is equal to the old one. We have not changed the password.");
            }
            else {
                UserController.getInstance().changePassword(username, newPasswordField.getText());
                ComponentUtils.showInformationDialog("Password Changed", "The new password has been changed successfully. Please, remember the new password.");
            }
        }
        else {
            ComponentUtils.showErrorDialog("Invalid Password", "The old password is incorrect. Please, try again");
        }
        newPasswordField.setText("");
        oldPasswordField.setText("");
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
