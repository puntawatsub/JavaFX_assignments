package application.controller;

import application.view.CurrencyConverterView;
import dao.CurrencyDao;
import entity.CurrencyEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NewCurrencyViewController {

    @FXML
    private TextField abbreField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField rateField;

    @FXML
    private Button saveButton;

    private Stage stage;
    private CurrencyDao dao;
    private CurrencyConverterView view;
    private CurrencyEntity result;

    @FXML
    void onCancel(ActionEvent event) {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    void onSave(ActionEvent event) {
        String rateText = rateField.getText().replaceAll(",", ".");
        CurrencyEntity entity = new CurrencyEntity(abbreField.getText(), nameField.getText(), Double.parseDouble(rateText));
        if (dao != null) {
            dao.persist(entity);
            result = entity;
            stage.close();
        }
    }

    public void init(Stage stage, CurrencyDao dao, CurrencyConverterView view) {
        rateField.textProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal.matches("\\d*([.,]\\d*)?")) {
                rateField.setText(oldVal);
            }
        });
        this.stage = stage;
        this.dao = dao;
        this.view = view;
    }

    public CurrencyEntity getResult() {
        return result;
    }
}
