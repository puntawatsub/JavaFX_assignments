package entity;

import jakarta.persistence.*;

@Entity
public class Transaction {
    @ManyToOne
    private CurrencyEntity fromCurrency;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    @ManyToOne
    private CurrencyEntity toCurrency;

    /**
     * @param fromCurrency
     * @param amount
     * @param toCurrency
     */
    public Transaction(CurrencyEntity fromCurrency, double amount, CurrencyEntity toCurrency) {
        this.fromCurrency = fromCurrency;
        this.amount = amount;
        this.toCurrency = toCurrency;
    }


    public Transaction() {}

    public CurrencyEntity getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(CurrencyEntity fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CurrencyEntity getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(CurrencyEntity toCurrency) {
        this.toCurrency = toCurrency;
    }
}
