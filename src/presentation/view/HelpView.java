package presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class HelpView extends VBox {
    public HelpView() {
        setSpacing(10);
        setPadding(new Insets(5));
        setAlignment(Pos.TOP_CENTER);

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        String path = new File("").getAbsolutePath();
        path += "\\docs\\proba.html";
        try {
            File file = new File(path);
            URL url = file.toURI().toURL();
            webEngine.load(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        getChildren().addAll(webView);
    }
}
