package presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import presentation.utils.ComponentUtils;
import presentation.view.components.RaisedButton;

public class LoadView extends GridPane {
    public LoadView() {
        VBox newGameBox = new VBox();
        VBox savedGameBox = new VBox();

        RaisedButton newGameButton = new RaisedButton("Start Game");
        RaisedButton savedGameButton = new RaisedButton("Start Game");

        getColumnConstraints().addAll(ComponentUtils.createColumnConstraint(50), ComponentUtils.createColumnConstraint(50));
        getRowConstraints().addAll(ComponentUtils.createRowConstraint(100));

        add(createBorderPane("New Game", newGameBox, newGameButton), 0, 0);
        add(createBorderPane("Saved Games", savedGameBox, savedGameButton), 1, 0);
    }

    private BorderPane createBorderPane(String title, VBox mainContent, RaisedButton button) {
        BorderPane result = new BorderPane();
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font(25));
        titleLabel.setTextFill(Color.WHITE);
        BorderPane.setMargin(titleLabel, new Insets(30));
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        result.setTop(titleLabel);
        BorderPane.setMargin(mainContent, new Insets(30));
        mainContent.setSpacing(10);
        mainContent.setAlignment(Pos.CENTER);
        result.setCenter(mainContent);
        BorderPane.setMargin(button, new Insets(30));
        BorderPane.setAlignment(button, Pos.CENTER);
        result.setBottom(button);
        return result;
    }
}
