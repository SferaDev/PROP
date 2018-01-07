package presentation.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import presentation.controller.PresentationController;
import presentation.utils.ComponentUtils;
import presentation.utils.LocaleUtils;
import presentation.view.components.RaisedButton;

/**
 * The User View
 *
 * @author Oriol Borrell Roig
 */
public class UserView extends VBox {

    /**
     * Loads the UserView to nebula.
     */
    public UserView() {
        setSpacing(10);
        setPadding(new Insets(30));
        setAlignment(Pos.TOP_CENTER);

        Label textUsernameLabel = createLabel(LocaleUtils.getInstance().getString("WELCOME") + ", " +
                PresentationController.getInstance().getUsername() + "!", 25);

        GridPane mainContent = new GridPane();
        mainContent.getColumnConstraints().addAll(ComponentUtils.createColumnConstraint(50),
                ComponentUtils.createColumnConstraint(50));
        mainContent.getRowConstraints().addAll(ComponentUtils.createRowConstraint(100));

        getChildren().addAll(textUsernameLabel, mainContent);

        Label textPasswordLabel = createLabel(LocaleUtils.getInstance().getString("CHANGE_PASSWORD"), 20);
        Label textLanguageLabel = createLabel(LocaleUtils.getInstance().getString("INTERFACE_LANGUAGE"), 20);

        JFXPasswordField oldPasswordField = createPasswordField(LocaleUtils.getInstance().getString("OLD_PASSWORD"));
        JFXPasswordField newPasswordField = createPasswordField(LocaleUtils.getInstance().getString("NEW_PASSWORD"));

        oldPasswordField.requestFocus();

        RaisedButton changePasswordButton = new RaisedButton(LocaleUtils.getInstance().getString("CHANGE"));
        changePasswordButton.setOnAction(event -> {
            PresentationController.getInstance().requestPasswordChange(oldPasswordField.getText(), newPasswordField.getText());
            oldPasswordField.setText("");
            newPasswordField.setText("");
            oldPasswordField.requestFocus();
        });

        oldPasswordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER) && !newPasswordField.getText().equals(""))
                changePasswordButton.fire();
            else if (event.getCode().equals(KeyCode.ENTER)) newPasswordField.requestFocus();
        });

        newPasswordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) changePasswordButton.fire();
        });

        JFXComboBox<String> languageComboBox = new JFXComboBox<>();
        languageComboBox.setBackground(new Background(new BackgroundFill(Color.web("#cfd8dc"), null, null)));
        for (LocaleUtils.Language language : LocaleUtils.Language.values()) {
            languageComboBox.getItems().add(language.name());
        }
        languageComboBox.getSelectionModel().select(LocaleUtils.getInstance().getLanguage().name());
        languageComboBox.setOnAction(event -> {
            PresentationController.getInstance().requestChangeLanguage(
                    LocaleUtils.Language.valueOf(languageComboBox.getSelectionModel().getSelectedItem()));
            Stage stage = (Stage) getScene().getWindow();
            stage.close();
            PresentationController.getInstance().launchNebulaForm(stage);
        });

        VBox changePasswordBox = new VBox();
        VBox changeLanguageBox = new VBox();

        changePasswordBox.setSpacing(20);
        changeLanguageBox.setSpacing(20);

        changePasswordBox.setAlignment(Pos.TOP_CENTER);
        changeLanguageBox.setAlignment(Pos.TOP_CENTER);

        changePasswordBox.getChildren().addAll(textPasswordLabel, oldPasswordField, newPasswordField, changePasswordButton);
        changeLanguageBox.getChildren().addAll(textLanguageLabel, languageComboBox);

        mainContent.add(changePasswordBox, 0, 0);
        mainContent.add(changeLanguageBox, 1, 0);
    }

    private JFXPasswordField createPasswordField(String title) {
        JFXPasswordField result = new JFXPasswordField();
        result.setPromptText(title);
        result.setPadding(new Insets(5));
        result.setBackground(new Background(new BackgroundFill(Color.web("#cfd8dc"), null, null)));
        return result;
    }

    private Label createLabel(String text, int size) {
        Label result = new Label();
        VBox.setMargin(result, new Insets(10));
        result.setAlignment(Pos.CENTER);
        result.setTextAlignment(TextAlignment.CENTER);
        result.setTextFill(Color.WHITE);
        result.setFont(Font.font(size));
        result.setText(text);
        return result;
    }
}
