package application.model;

import dao.CurrencyDao;
import entity.CurrencyEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyCollector {

    private CurrencyDao dao;

    public CurrencyCollector() {};

    private CurrencyDao getDao() {
        if (dao == null) {
            dao = new CurrencyDao();
        }
        return dao;
    }

    /**
     * Add new currency
     * @param currencyAbb Currency abbreviation
     * @param currencyName Currency name
     * @param USD_val Currency value in USD
     */
//    public void addCurrency(String currencyAbb, String currencyName, Double USD_val) {
//        this.USD_val.put(currencyAbb, new String[] {currencyName, Double.toString(USD_val)});
//        this.currency_abb.add(currencyAbb);
//    }

    /**
     * convert value from one currency to another
     * @param c1 source currency abbreviation
     * @param c2 target currency abbreviation
     * @param value value
     */
    public double convertTo(String c1, String c2, Double value) throws SQLException {
        double c1_usd = getDao().getExchangeRate(c1);
        double c2_usd = getDao().getExchangeRate(c2);

        if (!Double.isNaN(c1_usd) && !Double.isNaN(c2_usd)) {
            return (value / c2_usd) * c1_usd;
        }
        throw new IllegalArgumentException("Currency not found");
    }

    public List<String> getCurrencies() throws SQLException {
        return getDao().getAllCurrencyAbbr();
    }

    /**
     *
     * @param abb currency abbreviation
     * @return currency full name
     */
    public String getCurrencyName(String abb) throws SQLException {
        return getDao().getCurrencyName(abb);
    }

}
