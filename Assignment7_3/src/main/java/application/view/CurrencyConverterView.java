package application.view;

import application.controller.CurrencyConverterController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import de.jangassen.MenuToolkit;

public class CurrencyConverterView extends Application {

    private CurrencyConverterController controller;

    private ChoiceBox choiceBoxSource = new ChoiceBox<>();;
    private ChoiceBox choiceBoxTarget = new ChoiceBox<>();;
    private TextField source = new TextField();
    private TextField target = new TextField();

    private Alert alert = new Alert(Alert.AlertType.ERROR, "Please select currency or fill the number field.");

    private Alert SQLAlert = new Alert(Alert.AlertType.ERROR);

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        loadMain();
    }

    public void loadMain() {
        stage = new Stage();
        stage.setTitle("Currency Converter");
        stage.setResizable(false);
        target.setEditable(false);


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

        // input filtering
        source.textProperty().addListener((observable, oldValue, newValue) -> {
            // NOTE: ChatGPT generated regex
            if (!newValue.matches("\\d*([.,]\\d{0,2})?")) {
                source.setText(oldValue);
            }
        });

        choiceBoxTarget.setOnAction(event -> {
            controller.onTargetChoiceBoxChange();
            choiceBoxTarget.getItems().remove("Select");
        });

        convertButton.setOnAction(event -> {
            controller.convertButtonPressed();
        });

        hBox.getStyleClass().add("hBox");

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New Currency");
        newItem.setOnAction(event -> {
            try {
                controller.newCurrency();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fileMenu.getItems().add(newItem);
        String OS = System.getProperty("os.name").toLowerCase();
        Scene scene;
        if (!(OS.contains("darwin") || OS.contains("mac"))) {
            newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
            menuBar.getMenus().add(fileMenu);
            VBox root = new VBox(hBox);
            root.getChildren().addFirst(menuBar);
            scene = new Scene(root);
            stage.setScene(scene);
        } else {
            MenuToolkit tk = MenuToolkit.toolkit();
            newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.META_DOWN));
            menuBar.getMenus().addAll(tk.createDefaultApplicationMenu("Currency") ,fileMenu);
            scene = new Scene(hBox);
            stage.setScene(scene);
            MenuToolkit.toolkit().setMenuBar(stage, menuBar);
        }

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        if (!SQLAlert.isShowing()) {
            stage.show();
        }
    }

    @Override
    public void init() {
        controller = new CurrencyConverterController(this);
    }

    public void setChoiceBoxSource(List<String> list) {
        choiceBoxSource.setValue(list.getFirst());
        choiceBoxSource.setItems(FXCollections.observableList(list));
    }

    public void setChoiceBoxTarget(List<String> list) {
        choiceBoxTarget.setValue(list.getFirst());
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
        Platform.runLater(() -> {
            alert.show();
        });
    }

    public String[] getChoiceBoxes() {
        return new String[]{(String) choiceBoxSource.getValue(), (String) choiceBoxTarget.getValue()};
    }

    public void showSQLError() {
        System.err.println("AHHHH WHYYYY CHECK YOUR DATABASE");
        Platform.runLater(() -> {
            SQLAlert.setTitle("Database Error");
            SQLAlert.setHeaderText("Cannot retrieve currency data");
            SQLAlert.setContentText("Database not found or no currency data");
            SQLAlert.showAndWait().ifPresent(buttonType -> {
                Platform.exit();
            });
        });
    }
}
