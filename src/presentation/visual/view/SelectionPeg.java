package presentation.visual.view;

import com.jfoenix.controls.JFXNodesList;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class SelectionPeg extends JFXNodesList {
    private ColorPeg mainPeg;

    public SelectionPeg(String mainColor, ArrayList<String> availableColors) {
        setRotate(180);
        setSpacing(10);
        setAlignment(Pos.CENTER);

        mainPeg = new ColorPeg(mainColor);
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
