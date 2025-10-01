package dao;

import datasource.DbConnection;
import datasource.JpaConnection;
import entity.CurrencyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public List<CurrencyEntity> getAllCurrencies() {
        EntityManager em = JpaConnection.getInstance();
        return (List<CurrencyEntity>) em.createQuery("select e from CurrencyEntity e").getResultList();
    }

    public CurrencyEntity getCurrency(String a) {
        EntityManager em = JpaConnection.getInstance();
        Query query = em.createQuery("SELECT e FROM CurrencyEntity e WHERE e.abbreviation = :abb");
        query.setParameter("abb", a);
        return (CurrencyEntity) query.getSingleResult();
    }

    public List<String> getAllCurrencyAbbr() {
        EntityManager em = JpaConnection.getInstance();
        Query query = em.createQuery("SELECT e.abbreviation FROM CurrencyEntity e");
        return query.getResultList();
    }

    public double getExchangeRate(String a) {
        EntityManager em = JpaConnection.getInstance();
        Query query = em.createQuery("SELECT e.val_usd FROM CurrencyEntity e WHERE e.abbreviation = :abb");
        query.setParameter("abb", a);
        return (double) query.getSingleResult();
    }

    public String getCurrencyName(String a) {
        if (a != null) {
            EntityManager em = JpaConnection.getInstance();
            Query query = em.createQuery("SELECT e.currency_name FROM CurrencyEntity e WHERE e.abbreviation = :abb");
            System.out.println("Query: " + a);
            query.setParameter("abb", a);
            return (String) query.getSingleResult();
        }
        return null;
    }

    public void persist(CurrencyEntity c) {
        EntityManager em = JpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }

}
