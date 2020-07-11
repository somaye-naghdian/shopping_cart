package dao;

import entity.ShoppingCart;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ShoppingCartDao {
    Session session = null;
    Transaction transaction = null;

    public void insertShoppingCart(ShoppingCart shoppingCart) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(shoppingCart);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
