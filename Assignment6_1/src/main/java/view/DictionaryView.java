package view;

import controller.DictionaryController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class DictionaryView extends Application {

    private DictionaryController controller;

    private Label result = new Label("");
    private TextField searchField = new TextField();

    @Override
    public void start(Stage stage) {

        stage.setResizable(false);

        VBox vBox = new VBox();

        HBox hBox = new HBox();

        searchField.setPromptText("Entry");
        searchField.setMaxWidth(Double.MAX_VALUE);
        searchField.getStyleClass().add("search-input");
        searchField.setMinWidth(300);

        Button searchButton = new Button("Search");

        hBox.getChildren().addAll(searchField, searchButton);
        hBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(searchField, Priority.ALWAYS);
        HBox.setMargin(searchButton, new Insets(0, 0, 0, 10));
        result.getStyleClass().add("result-text");

        vBox.getChildren().addAll(hBox);
        vBox.getChildren().add(result);
        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(hBox, new Insets(10, 10, 10, 10));
        VBox.setMargin(result, new Insets(50, 0, 50, 0));

        Scene scene = new Scene(vBox);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(scene);

        stage.show();

        searchButton.setOnAction(event -> {
            controller.searchButtonPressed(searchField.getText());
        });
    }

    @Override
    public void init() {
        controller = new DictionaryController(this);
    }

    public void updateResult(String s) {
        result.setText(s);
    }

    public void emptySearchField() {
        searchField.setText("");
    }
}
