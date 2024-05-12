package io.pcholkin.java.javalearn;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    public VBox panel;
    public Label Text;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label listVW;

    String mainurl = "https://itproger.com/spravka/";


    @Override
    public void initialize( URL url, ResourceBundle resourceBundle) {

        ObservableList<String> names = FXCollections.observableArrayList();
        ObservableList<String> urls = FXCollections.observableArrayList(names);
        try{
            Document page = Jsoup.connect(mainurl + "java").get();
            Elements elements = page.select("body > div#wrapper > div#content > div.main-wrapper.container > main.left > div.block > div.spravochnik > div > div > a");
            for(Element element: elements){

                String text = "Что такое " + element.text() + "?";
                String href = element.attr("href");

                urls.add(href);
                names.add(text);

            }
            listView.setItems(names);

        } catch (Exception e){
            e.printStackTrace();
        }

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Stage stage = (Stage) listView.getScene().getWindow();
                int pos = listView.getSelectionModel().getSelectedIndex();
                String href = urls.get(pos);
                String newUrl = mainurl + href;
                System.out.println(newUrl);

                FXMLLoader fxmlLoader = new FXMLLoader(InfoController.class.getResource("info-view.fxml"));
                InfoController controller = new InfoController(newUrl);
                fxmlLoader.setController(controller);
                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Scene newScene = new Scene(root, 800, 500);

                stage.setScene(newScene);
            }
        });
    }


}