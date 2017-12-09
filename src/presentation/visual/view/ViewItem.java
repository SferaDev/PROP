package presentation.visual.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

abstract class ViewItem extends Parent {
    ViewItem(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/layout/" + name + ".fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
