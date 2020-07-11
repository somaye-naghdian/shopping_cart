package dao;

import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDao {

    private static Logger logger;
    public void insertUser(User user) {
        logger = LogManager.getLogger(user.getUserName());
        logger.info("register");


        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("successfully insert");
    }


    public User passwordValidation(String inputUsername, String inputPassword) {
        List<User> userList;
        String passwordQuery = null;

        User resultUser = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class, "u");
        criteria.add(Restrictions.eq("u.userName", inputUsername));
        userList = criteria.list();

        transaction.commit();
        session.close();
        for (Iterator iterator = userList.iterator(); iterator.hasNext(); ) {
            resultUser = (User) iterator.next();
            passwordQuery = resultUser.getPassword();
        }
        try{
        if (passwordQuery.equals(inputPassword)) {
            for (Iterator iterator = userList.iterator(); iterator.hasNext(); ) {
                resultUser = (User) iterator.next();
                resultUser.setName(resultUser.getName());
                resultUser.setFamily(resultUser.getFamily());
                resultUser.setAge(resultUser.getAge());
                resultUser.setPhone(resultUser.getPhone());
                resultUser.setEmail(resultUser.getEmail());
                resultUser.setUserName(resultUser.getUserName());
                resultUser.setPassword(resultUser.getPassword());
                resultUser.setAddress(resultUser.getAddress());
            }
        }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return resultUser;
    }


    public ArrayList<User> getUserList() {
        List<User> users = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("from User", User.class).list();

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<User>) users;
    }

    public boolean searchDuplicateUserName(String username) {
        User user = null;
        try {
          Session  session = HibernateUtil.getSessionFactory().openSession();
           Transaction transaction = session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class, "u");
            criteria.add(Restrictions.eq("u.userName", username));
            List users = criteria.list();
            for (Iterator iterator = users.iterator(); iterator.hasNext(); ) {
                user = (User) iterator.next();
            }
            if (user != null) {
                return false;
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
