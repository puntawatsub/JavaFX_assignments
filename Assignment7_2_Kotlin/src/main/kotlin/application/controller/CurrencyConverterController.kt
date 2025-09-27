package application.controller

import application.model.CurrencyCollector
import application.view.CurrencyConverterView
import java.sql.SQLException
import java.text.DecimalFormat

class CurrencyConverterController(private val view: CurrencyConverterView) {
    private val currencyCollector: CurrencyCollector

    init {
        currencyCollector = CurrencyCollector()

        val tempList = ArrayList<String?>(this.currencies)
        tempList.addFirst("Select")
        val tempList1 = ArrayList<String?>(tempList)
        view.setChoiceBoxSource(tempList)
        view.setChoiceBoxTarget(tempList1)
        view.setSourcePlaceholder("Select Currency")
        view.setTargetPlaceholder("Select Currency")
        view.setChoiceBoxSourceValue("Select")
        view.setChoiceBoxTargetValue("Select")
    }

    val currencies: MutableList<String?>?
        get() {
            try {
                return currencyCollector.currencies
            } catch (e: SQLException) {
                view.showSQLError()
            }
            return ArrayList<String?>()
        }

    /**
     * convert value from one currency to another
     * @param c1 source currency abbreviation
     * @param c2 target currency abbreviation
     * @param value value
     */
    fun convert(c1: String?, c2: String?, value: Double) {
        try {
            val result: Double = currencyCollector.convertTo(c1, c2, value)
            println(result)
            val df = DecimalFormat("0.00####")
            view.setTarget(df.format(result))
        } catch (e: SQLException) {
            view.showSQLError()
        }
    }

    fun convertButtonPressed() {
        try {
            var source: String = view.getSource()!!
            if (source.matches(".*[,].*".toRegex()) && !source.matches(".*[.].*".toRegex())) {
                source = source.replace("[,]".toRegex(), ".")
            }
            val value = source.toDouble()
            view.setSource(String.format("%.2f", value))
            val choiceBoxes: Array<String?> = view.choiceBoxes
            this.convert(choiceBoxes[0], choiceBoxes[1], value)
        } catch (e: Exception) {
            view.showAlert()
        }
    }

    fun onSourceChoiceBoxChange() {
        try {
            view.setSourcePlaceholder(currencyCollector.getCurrencyName(view.choiceBoxes[0]))
        } catch (e: SQLException) {
            view.showSQLError()
        }
    }

    fun onTargetChoiceBoxChange() {
        try {
            view.setTargetPlaceholder(currencyCollector.getCurrencyName(view.choiceBoxes[1]))
        } catch (e: SQLException) {
            view.showSQLError()
        }
    }
}