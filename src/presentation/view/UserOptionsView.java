package presentation.view;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import presentation.utils.LocaleUtils;

public class UserOptionsView extends VBox {
    private String username;

    public UserOptionsView() {
        setSpacing(10);
        setAlignment(Pos.TOP_LEFT);

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
        //changePasswordButton.setOnMouseClicked(event -> PresentationController.getInstance()
                //.requestPasswordChange(username, oldPasswordField.getText(), newPasswordField.getText()));

        Label textLanguageLabel = createLabel("Selected language: " + LocaleUtils.getInstance().getLanguage(), 18); // TODO: Strings
        getChildren().add(textLanguageLabel);

        JFXComboBox<String> languageComboBox = new JFXComboBox<>();
        for (LocaleUtils.Language language : LocaleUtils.Language.values()) {
            languageComboBox.getItems().add(language.name()); // TODO: Strings
        }
        languageComboBox.setPromptText("Select language.."); // TODO: Strings
        //PresentationController.getInstance().requestChangeLanguage(username, locale);
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

    public void setUsername(String username) {
        this.username = username;
    }
}
