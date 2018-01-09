package presentation.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import presentation.utils.LocaleUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * The Help View
 *
 * @author Oriol Borrell Roig
 */
public class HelpView extends VBox {
    /**
     * Loads the HelpView to nebula
     */
    public HelpView() {
        setSpacing(10);
        setPadding(new Insets(5));
        setAlignment(Pos.TOP_CENTER);
        getChildren().add(createLabel(LocaleUtils.getInstance().getString("HELP")));
        try {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            URL url = getClass().getResource("/resources/img/help2.gif").toURI().toURL();
            webEngine.load(url.toString());
            getChildren().addAll(webView);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Label createLabel(String text) {
        Label result = new Label();
        VBox.setMargin(result, new Insets(10));
        result.setAlignment(Pos.CENTER);
        result.setTextAlignment(TextAlignment.CENTER);
        result.setTextFill(Color.WHITE);
        result.setFont(Font.font(20));
        result.setText(text);
        return result;
    }
}
