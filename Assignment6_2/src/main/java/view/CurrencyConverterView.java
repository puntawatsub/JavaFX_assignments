package view;

import controller.CurrencyConverterController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CurrencyConverterView extends Application {

    private CurrencyConverterController controller;

    private ChoiceBox choiceBoxSource = new ChoiceBox<>();
    private ChoiceBox choiceBoxTarget = new ChoiceBox<>();
    private TextField source = new TextField();
    private TextField target = new TextField();

    Alert alert = new Alert(Alert.AlertType.ERROR, "Please check the number format or select a currency.");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Currency Converter");
        stage.setResizable(false);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        source.setMinWidth(200);
        hBox.getChildren().add(source);
        HBox.setMargin(source, new Insets(10, 10, 10, 10));

        VBox vBox1 = new VBox();
        vBox1.getChildren().add(new Label("Select Currency"));
        vBox1.getChildren().add(choiceBoxSource);
        vBox1.setAlignment(Pos.CENTER);
        VBox.setMargin(choiceBoxSource, new Insets(2, 0, 0, 0));

        hBox.getChildren().add(vBox1);
        HBox.setMargin(vBox1, new Insets(5, 10, 10, 0));

        Button convertButton = new Button("Convert");
        convertButton.getStyleClass().add("convert-button");
        hBox.getChildren().add(convertButton);
        HBox.setMargin(convertButton, new Insets(10, 10, 10, 0));

        choiceBoxSource.setOnAction(event -> {
            controller.onSourceChoiceBoxChange();
            choiceBoxSource.getItems().remove("Select");
        });

        target.setMinWidth(200);
        hBox.getChildren().add(target);
        HBox.setMargin(target, new Insets(10, 10, 10, 0));

        VBox vBox2 = new VBox();
        vBox2.getChildren().add(new Label("Select Currency"));
        vBox2.getChildren().add(choiceBoxTarget);
        vBox2.setAlignment(Pos.CENTER);
        VBox.setMargin(choiceBoxTarget, new Insets(2, 0, 0, 0));
        hBox.getChildren().add(vBox2);
        HBox.setMargin(vBox2, new Insets(5, 10, 10, 0));

        source.setOnKeyTyped(event -> {
            target.setText("");
        });

        choiceBoxTarget.setOnAction(event -> {
            controller.onTargetChoiceBoxChange();
            choiceBoxTarget.getItems().remove("Select");
        });

        convertButton.setOnAction(event -> {
            controller.convertButtonPressed();
        });

        hBox.getStyleClass().add("hBox");

        Scene scene = new Scene(hBox);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setScene(scene);

        target.setEditable(false);
        stage.show();
    }

    @Override
    public void init() {
        controller = new CurrencyConverterController(this);
    }

    public void setChoiceBoxSource(List<String> list) {
        choiceBoxSource.setItems(FXCollections.observableList(list));
    }

    public void setChoiceBoxTarget(List<String> list) {
        choiceBoxTarget.setItems(FXCollections.observableList(list));
    }

    public void setChoiceBoxSourceValue(String s) {
        choiceBoxSource.setValue(s);
    }

    public void setChoiceBoxTargetValue(String s) {
        choiceBoxTarget.setValue(s);
    }

    public String getSource() {
        return source.getText();
    }

    public String getTarget() {
        return target.getText();
    }

    public void setSource(String s) {
        source.setText(s);
    }

    public void setSourcePlaceholder(String s) {
        source.setPromptText(s);
    }

    public void setTarget(String s) {
        target.setText(s);
    }

    public void setTargetPlaceholder(String s) {
        target.setPromptText(s);
    }

    public void showAlert() {
        alert.show();
    }

    public String[] getChoiceBoxes() {
        return new String[]{(String) choiceBoxSource.getValue(), (String) choiceBoxTarget.getValue()};
    }
}
