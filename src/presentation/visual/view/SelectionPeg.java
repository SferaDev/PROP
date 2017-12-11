package presentation.visual.view;

import com.jfoenix.controls.JFXNodesList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class SelectionPeg extends JFXNodesList {
    private ColorPeg mainPeg;

    // PRE: availableColors.size() > 0
    public SelectionPeg(ArrayList<String> availableColors) {
        setRotate(180);
        setSpacing(10);
        setAlignment(Pos.CENTER);

        mainPeg = new ColorPeg(availableColors.get(0));
        addAnimatedNode(mainPeg);

        for (String color : availableColors) {
            ControlPeg peg = new ControlPeg(ControlPeg.Type.none);
            peg.setStyle("-fx-background-color: " + color);
            peg.setOnMouseClicked(event -> mainPeg.setColor(color));
            addAnimatedNode(peg);
        }
    }

    public String getColor() {
        return mainPeg.getColor();
    }
}
