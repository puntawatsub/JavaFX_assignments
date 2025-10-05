package entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "currency")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String abbreviation;
    private String currency_name;
    private double val_usd;

    public CurrencyEntity(String abbreviation, String currency_name, double val_usd) {
        this.abbreviation = abbreviation;
        this.currency_name = currency_name;
        this.val_usd = val_usd;
    }

    public CurrencyEntity() {
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public double getVal_usd() {
        return val_usd;
    }

    public void setVal_usd(double val_usd) {
        this.val_usd = val_usd;
    }
}


