package dao;

import datasource.DbConnection;
import entity.CurrencyEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public List<CurrencyEntity> getAllCurrencies() throws SQLException {
        Connection conn = null;
        List<CurrencyEntity> resultCurrencies = null;
        conn = DbConnection.getConnection();
        String query = "SELECT abbreviation, currency_name, val_usd FROM CURRENCY;";
        resultCurrencies = new ArrayList<>();
        ResultSet rs = conn.createStatement().executeQuery(query);
        while (rs.next()) {
            String abbreviation = rs.getString(1);
            String currency_name = rs.getString(2);
            double val_usd = rs.getDouble(3);
            resultCurrencies.add(new CurrencyEntity(abbreviation, currency_name, val_usd));
        }
        return resultCurrencies;
    }

    public CurrencyEntity getCurrency(String a) throws SQLException {
        Connection conn = null;
        CurrencyEntity currencyEntity = null;
        conn = DbConnection.getConnection();
        String query = "SELECT abbreviation, currency_name, val_usd FROM CURRENCY WHERE abbreviation=?;";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, a);
        int count = 0;

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            count++;
            if (count > 1) {
                currencyEntity = null;
                break;
            }
            String abbreviation = rs.getString(1);
            String currency_name = rs.getString(2);
            double val_usd = rs.getDouble(3);
            currencyEntity = new CurrencyEntity(abbreviation, currency_name, val_usd);
        }
        return currencyEntity;
    }

    public List<String> getAllCurrencyAbbr() throws SQLException {
        Connection conn = DbConnection.getConnection();
        String query = "SELECT abbreviation FROM CURRENCY;";
        ResultSet rs = conn.createStatement().executeQuery(query);
        List<String> result = new ArrayList<>();
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public double getExchangeRate(String a) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String query = "SELECT val_usd FROM CURRENCY WHERE abbreviation=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, a);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        double result = 0.0;
        while (rs.next()) {
            count++;
            if (count > 1) {
                result = 0;
                break;
            }
            result = rs.getDouble(1);
        }
        return result;
    }

    public String getCurrencyName(String a) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String query = "SELECT currency_name FROM CURRENCY WHERE abbreviation=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, a);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        String result = null;
        while (rs.next()) {
            count++;
            if (count > 1) {
                result = null;
                break;
            }
            result = rs.getString(1);
        }
        return result;
    }

}
