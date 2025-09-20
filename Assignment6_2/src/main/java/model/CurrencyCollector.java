package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyCollector {

    private Map<String, String[]> USD_val;
    private ArrayList<String> currency_abb;

    public CurrencyCollector() {
        this.USD_val = new HashMap<>();
        this.currency_abb = new ArrayList<>();
    }

    /**
     * Add new currency
     * @param currencyAbb Currency abbreviation
     * @param currencyName Currency name
     * @param USD_val Currency value in USD
     */
    public void addCurrency(String currencyAbb, String currencyName, Double USD_val) {
        this.USD_val.put(currencyAbb, new String[] {currencyName, Double.toString(USD_val)});
        this.currency_abb.add(currencyAbb);
    }

    /**
     * convert value from one currency to another
     * @param c1 source currency abbreviation
     * @param c2 target currency abbreviation
     * @param value value
     */
    public double convertTo(String c1, String c2, Double value) {
        double c1_usd = Double.parseDouble(this.USD_val.get(c1)[1]);
        double c2_usd = Double.parseDouble(this.USD_val.get(c2)[1]);

        if (!Double.isNaN(c1_usd) && !Double.isNaN(c2_usd)) {
            return (value / c1_usd) * c2_usd;
        }
        throw new IllegalArgumentException("Currency not found");
    }

    public List<String> getCurrencies() {
        return currency_abb;
    }

    /**
     *
     * @param abb currency abbreviation
     * @return currency full name
     */
    public String getCurrencyName(String abb) {
        return this.USD_val.get(abb)[0];
    }

}
