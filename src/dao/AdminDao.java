package dao;

import entity.Admin;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class AdminDao {
    Session session = null;
    Transaction transaction = null;

    public Admin passwordValidation(String inputUsername, String inputPassword) {
        String passwordQuery = null;
        List<Admin> adminList;
        Admin admin = new Admin();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String hql = " from Admin a where a.username =:inputUsername";
            Query query = session.createQuery(hql, Admin.class).setParameter("inputUsername", inputUsername);
            adminList = query.list();

            for (Iterator iterator = adminList.iterator(); iterator.hasNext(); ) {
                admin = (Admin) iterator.next();
                passwordQuery = admin.getPassword();
            }
            if (passwordQuery.equals(inputPassword)) {
                admin.setUsername(inputUsername);
                admin.setPassword(passwordQuery);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return admin;
    }
}
