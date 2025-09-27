package dao

import datasource.DbConnection
import entity.CurrencyEntity
import java.sql.Connection
import java.sql.SQLException

class CurrencyDao {
    @get:Throws(SQLException::class)
    val allCurrencies: ArrayList<CurrencyEntity?>
        get() {
            var conn: Connection? = null
            var resultCurrencies: MutableList<CurrencyEntity?>? = null
            conn = DbConnection.connection
            val query = "SELECT abbreviation, currency_name, val_usd FROM CURRENCY;"
            resultCurrencies = ArrayList<CurrencyEntity?>()
            val rs = conn!!.createStatement().executeQuery(query)
            while (rs.next()) {
                val abbreviation = rs.getString(1)
                val currency_name = rs.getString(2)
                val val_usd = rs.getDouble(3)
                resultCurrencies.add(CurrencyEntity(abbreviation, currency_name, val_usd))
            }
            return resultCurrencies
        }

    @Throws(SQLException::class)
    fun getCurrency(a: String?): CurrencyEntity? {
        var conn: Connection? = null
        var currencyEntity: CurrencyEntity? = null
        conn = DbConnection.connection
        val query = "SELECT abbreviation, currency_name, val_usd FROM CURRENCY WHERE abbreviation=?;"
        val ps = conn!!.prepareStatement(query)
        ps.setString(1, a)
        var count = 0

        val rs = ps.executeQuery()

        while (rs.next()) {
            count++
            if (count > 1) {
                currencyEntity = null
                break
            }
            val abbreviation = rs.getString(1)
            val currency_name = rs.getString(2)
            val val_usd = rs.getDouble(3)
            currencyEntity = CurrencyEntity(abbreviation, currency_name, val_usd)
        }
        return currencyEntity
    }

    @get:Throws(SQLException::class)
    val allCurrencyAbbr: MutableList<String?>?
        get() {
            val conn: Connection? = DbConnection.connection
            val query = "SELECT abbreviation FROM CURRENCY;"
            val rs = conn!!.createStatement().executeQuery(query)
            val result: MutableList<String?> = ArrayList<String?>()
            while (rs.next()) {
                result.add(rs.getString(1))
            }
            if (result.isEmpty()) {
                return null
            }
            return result
        }

    @Throws(SQLException::class)
    fun getExchangeRate(a: String?): Double {
        val conn: Connection? = DbConnection.connection
        val query = "SELECT val_usd FROM CURRENCY WHERE abbreviation=?"
        val ps = conn!!.prepareStatement(query)
        ps.setString(1, a)
        val rs = ps.executeQuery()
        var count = 0
        var result = 0.0
        while (rs.next()) {
            count++
            if (count > 1) {
                result = 0.0
                break
            }
            result = rs.getDouble(1)
        }
        return result
    }

    @Throws(SQLException::class)
    fun getCurrencyName(a: String?): String? {
        val conn: Connection? = DbConnection.connection
        val query = "SELECT currency_name FROM CURRENCY WHERE abbreviation=?"
        val ps = conn!!.prepareStatement(query)
        ps.setString(1, a)
        val rs = ps.executeQuery()
        var count = 0
        var result: String? = null
        while (rs.next()) {
            count++
            if (count > 1) {
                result = null
                break
            }
            result = rs.getString(1)
        }
        return result
    }
}