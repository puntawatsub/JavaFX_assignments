package application.model

import dao.CurrencyDao
import java.lang.Double
import java.sql.SQLException
import kotlin.IllegalArgumentException
import kotlin.String
import kotlin.Throws
import kotlin.times

class CurrencyCollector {
    private var dao: CurrencyDao? = null
        get() {
            if (field == null) {
                field = CurrencyDao()
            }
            return field
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
    @Throws(SQLException::class)
    fun convertTo(c1: String?, c2: String?, value: kotlin.Double): kotlin.Double {
        val c1_usd: kotlin.Double = this.dao!!.getExchangeRate(c1)
        val c2_usd: kotlin.Double = this.dao!!.getExchangeRate(c2)

        if (!Double.isNaN(c1_usd) && !Double.isNaN(c2_usd)) {
            return (value / c2_usd) * c1_usd
        }
        throw IllegalArgumentException("Currency not found")
    }

    @get:Throws(SQLException::class)
    val currencies: MutableList<String?>?
        get() = this.dao!!.allCurrencyAbbr

    /**
     *
     * @param abb currency abbreviation
     * @return currency full name
     */
    @Throws(SQLException::class)
    fun getCurrencyName(abb: String?): String? {
        return this.dao!!.getCurrencyName(abb)
    }
}