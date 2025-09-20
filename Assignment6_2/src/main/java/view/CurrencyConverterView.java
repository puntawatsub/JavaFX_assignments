package view;

import controller.CurrencyConverterController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class CurrencyConverterView extends Application {

    private CurrencyConverterController controller;

    private ChoiceBox choiceBoxSource = new ChoiceBox<>();
    private ChoiceBox choiceBoxTarget = new ChoiceBox<>();
    private TextField source = new TextField();
    private TextField target = new TextField();

    Alert alert = new Alert(Alert.AlertType.ERROR, "Please check the number format.");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Currency Converter");
        target.setDisable(true);
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
