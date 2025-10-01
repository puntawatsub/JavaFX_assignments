package application.controller;

import application.model.CurrencyCollector;
import application.view.CurrencyConverterView;
import dao.CurrencyDao;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrencyConverterController {

    private CurrencyCollector currencyCollector;
    private CurrencyConverterView view;

    public CurrencyConverterController(CurrencyConverterView view) {
        this.view = view;
        currencyCollector = new CurrencyCollector();

        ArrayList<String> tempList = new ArrayList<>(getCurrencies());
        tempList.addFirst("Select");
        ArrayList<String> tempList1 = new ArrayList<>(tempList);
        view.setChoiceBoxSource(tempList);
        view.setChoiceBoxTarget(tempList1);
        view.setSourcePlaceholder("Select Currency");
        view.setTargetPlaceholder("Select Currency");
        view.setChoiceBoxSourceValue("Select");
        view.setChoiceBoxTargetValue("Select");
    }

    public List<String> getCurrencies() {
        try {
            return currencyCollector.getCurrencies();
        } catch (SQLException e) {
            view.showSQLError();
        }
        return new ArrayList<>();
    }

    /**
     * convert value from one currency to another
     * @param c1 source currency abbreviation
     * @param c2 target currency abbreviation
     * @param value value
     */
    public void convert(String c1, String c2, Double value) {
        try {
            double result = currencyCollector.convertTo(c1, c2, value);
            if (Double.isInfinite(result)) {
                throw new NumberFormatException("Cannot divide by zero");
            } else if (Double.isNaN(result)) {
                throw new NumberFormatException("Value is not a number");
            }
            DecimalFormat df = new DecimalFormat("0.00####");
            view.setTarget(df.format(result));
        } catch (SQLException e) {
            view.showSQLError();
        } catch (NumberFormatException e) {
            view.showAlert();
        }
    }

    public void convertButtonPressed() {
        try {
            String source = view.getSource();
            if (source.matches(".*[,].*") && !source.matches(".*[.].*")) {
                source = source.replaceAll("[,]", ".");
            }
            Double value = Double.parseDouble(source);
            view.setSource(String.format("%.2f", value));
            String[] choiceBoxes = view.getChoiceBoxes();
            this.convert(choiceBoxes[0], choiceBoxes[1], value);
        } catch (Exception e) {
            view.showAlert();
        }
    }

    public void onSourceChoiceBoxChange() {
        try {
            view.setSourcePlaceholder(currencyCollector.getCurrencyName(view.getChoiceBoxes()[0]));
        } catch (SQLException e) {
            view.showSQLError();
        }
    }

    public void onTargetChoiceBoxChange() {
        try {
            view.setTargetPlaceholder(currencyCollector.getCurrencyName(view.getChoiceBoxes()[1]));
        } catch (SQLException e) {
            view.showSQLError();
        }
    }

    public void newCurrency() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/newCurrency_view.fxml"));
        Scene scene = new Scene(loader.load());
        NewCurrencyViewController controller = loader.getController();
        controller.init(stage, currencyCollector.getDao(), view);
        stage.setScene(scene);
        stage.showAndWait();
        Platform.runLater(() -> {
            view.setChoiceBoxTarget(new ArrayList<>(getCurrencies()));
            view.setChoiceBoxSource(new ArrayList<>(getCurrencies()));
            view.setChoiceBoxTargetValue(getCurrencies().get(0));
            view.setChoiceBoxSourceValue(getCurrencies().get(0));
        });
    }
}
