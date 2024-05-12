package io.pcholkin.java.javalearn;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {

    private String newUrl;

    @FXML
    private Text titleView;

    public InfoController(String newUrl) {
        this.newUrl = newUrl;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Document page = Jsoup.connect(newUrl).get();
            Elements element = page.select("body > div#wrapper > div#content > div.main-wrapper.container > main.left > h1.heading");
            titleView.setText(element.text());
            titleView.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
