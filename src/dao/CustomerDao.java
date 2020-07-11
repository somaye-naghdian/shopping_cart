package dao;

import entity.Customer;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerDao {
    Session session = null;
    Transaction transaction = null;

    public void insertCustomer(Customer customer) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("successfully insert");
    }


    public Customer passwordValidation(String inputUsername, String inputPassword) {
        List<Customer> customerList;
        String passwordQuery = null;

        Customer resultCustomer = null;

        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String hql = " from Customer c where c.userName=:username ";
        Query query = session.createQuery(hql, Customer.class);
        query.setParameter("username", inputUsername);
        customerList = query.list();

        transaction.commit();
        session.close();
        for (Iterator iterator = customerList.iterator(); iterator.hasNext(); ) {
            resultCustomer = (Customer) iterator.next();
            passwordQuery = resultCustomer.getPassword();
        }
        if (passwordQuery.equals(inputPassword)) {
            for (Iterator iterator = customerList.iterator(); iterator.hasNext(); ) {
                resultCustomer = (Customer) iterator.next();
                resultCustomer.setName(resultCustomer.getName());
                resultCustomer.setFamily(resultCustomer.getFamily());
                resultCustomer.setAge(resultCustomer.getAge());
                resultCustomer.setPhone(resultCustomer.getPhone());
                resultCustomer.setEmail(resultCustomer.getEmail());
                resultCustomer.setUserName(resultCustomer.getUserName());
                resultCustomer.setPassword(resultCustomer.getPassword());
                resultCustomer.setAddress(resultCustomer.getAddress());
            }
        }
        return resultCustomer;
    }

    public ArrayList<Customer> getCustomerList() {
        List<Customer> customers = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            customers = session.createQuery("From Customer", Customer.class).list();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (ArrayList<Customer>) customers;
    }

    public boolean searchDuplicateUserName(String username) {
        Customer customer = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Criteria criteria =session.createCriteria(Customer.class,"c");
            criteria.add(Restrictions.eq("c.userName",username));
            List customers = criteria.list();
            for (Iterator iterator = customers.iterator(); iterator.hasNext(); ) {
                customer = (Customer) iterator.next();
            }
            if (customer != null){
                return false;
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

}
