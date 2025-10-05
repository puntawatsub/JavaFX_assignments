package dao;

import datasource.JpaConnection;
import entity.Transaction;
import jakarta.persistence.EntityManager;

public class TransactionDao {
    public TransactionDao() {
    }

    public void persist(Transaction t) {
        EntityManager em = JpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }
}
