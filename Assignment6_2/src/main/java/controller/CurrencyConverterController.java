package controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.CurrencyCollector;
import view.CurrencyConverterView;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConverterController {

    private CurrencyCollector currencyCollector;
    private CurrencyConverterView view;

    public CurrencyConverterController(CurrencyConverterView view) {
        this.view = view;
        currencyCollector = new CurrencyCollector();
        currencyCollector.addCurrency("USD", "US Dollars", 1.0);
        currencyCollector.addCurrency("EUR", "Euro", 1.17);
        currencyCollector.addCurrency("JPY", "Japanese Yen", 0.0068);
        currencyCollector.addCurrency("GBP", "Pound Sterling", 1.35);
        currencyCollector.addCurrency("CNY", "Chinese Yuan", 0.14);

        ArrayList<String> tempList = new ArrayList<>(getCurrencies());
        tempList.addFirst("Select");
        view.setChoiceBoxSource(tempList);
        view.setChoiceBoxTarget(tempList);
        view.setSourcePlaceholder("Select Currency");
        view.setTargetPlaceholder("Select Currency");
        view.setChoiceBoxSourceValue("Select");
        view.setChoiceBoxTargetValue("Select");
    }

    public List<String> getCurrencies() {
        return currencyCollector.getCurrencies();
    }

    /**
     * convert value from one currency to another
     * @param c1 source currency abbreviation
     * @param c2 target currency abbreviation
     * @param value value
     */
    public void convert(String c1, String c2, Double value) {
        double result = currencyCollector.convertTo(c1, c2, value);
        view.setTarget(Double.toString(result));
    }

    public void convertButtonPressed() {
        try {
            Double value = Double.parseDouble(view.getSource());
            String[] choiceBoxes = view.getChoiceBoxes();
            this.convert(choiceBoxes[0], choiceBoxes[1], value);
        } catch (Exception e) {
            view.showAlert();
        }
    }

    public void onSourceChoiceBoxChange() {
        view.setSourcePlaceholder(currencyCollector.getCurrencyName(view.getChoiceBoxes()[0]));
    }

    public void onTargetChoiceBoxChange() {
        view.setTargetPlaceholder(currencyCollector.getCurrencyName(view.getChoiceBoxes()[1]));
    }
}
