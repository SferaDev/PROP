package presentation.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import presentation.controller.LocaleController;
import presentation.controller.PresentationController;

public class UserView extends VBox {

    public UserView() {
        setSpacing(10);
        setPadding(new Insets(15));
        setAlignment(Pos.TOP_LEFT);

        String username = PresentationController.getInstance().getUsername();
        Label textUsernameLabel = createLabel("Welcome, " + username, 25); // TODO: Strings
        getChildren().add(textUsernameLabel);

        Label textPasswordLabel = createLabel("Change password", 18);
        getChildren().add(textPasswordLabel);

        JFXPasswordField oldPasswordField = new JFXPasswordField();
        oldPasswordField.setPromptText("Old password");
        getChildren().add(oldPasswordField);

        JFXPasswordField newPasswordField = new JFXPasswordField();
        newPasswordField.setPromptText("New password");
        getChildren().add(newPasswordField);

        JFXButton changePasswordButton = new JFXButton("Change");
        getChildren().add(changePasswordButton);
        changePasswordButton.setOnMouseClicked(event -> PresentationController.getInstance()
                .requestPasswordChange(oldPasswordField.getText(), newPasswordField.getText()));

        Label textLanguageLabel = createLabel("User Interface Language", 18); // TODO: Strings
        getChildren().add(textLanguageLabel);

        JFXComboBox<String> languageComboBox = new JFXComboBox<>();
        for (LocaleController.Language language : LocaleController.Language.values()) {
            languageComboBox.getItems().add(language.name());
        }
        languageComboBox.getSelectionModel().select(LocaleController.getInstance().getLanguage().name());
        languageComboBox.setOnAction(event -> {
            PresentationController.getInstance().requestChangeLanguage(
                    LocaleController.Language.valueOf(languageComboBox.getSelectionModel().getSelectedItem()));
            Stage stage = (Stage) getScene().getWindow();
            stage.close();
            PresentationController.getInstance().launchNebulaForm(stage);
        });
        getChildren().add(languageComboBox);
    }

    private Label createLabel(String text, int size) {
        Label result = new Label();
        result.setAlignment(Pos.CENTER);
        result.setTextAlignment(TextAlignment.CENTER);
        result.setTextFill(Color.WHITE);
        result.setFont(Font.font(size));
        result.setText(text);
        return result;
    }
}
