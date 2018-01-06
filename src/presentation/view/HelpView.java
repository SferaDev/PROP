package presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class HelpView extends VBox {
    public HelpView() {
        setSpacing(10);
        setPadding(new Insets(5));
        setAlignment(Pos.TOP_CENTER);
        try {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            URL url = getClass().getResource("/resources/html/ManualUsuari.html").toURI().toURL();
            webEngine.load(url.toString());
            getChildren().addAll(webView);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
