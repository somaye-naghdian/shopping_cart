package service;

import dao.UserDao;
import entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
    private static Logger logger;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PurchaseService purchaseService ;

    public void signUpUser(User customer) {
        userDao.insertUser(customer);
    }

    public void signIn(String username, String password) {
        User customer = userDao.passwordValidation(username, password);

        if (userDao.passwordValidation(username, password) != null) {
            System.out.println("successful login");
            logger = LogManager.getLogger(username);
            logger.info("login");
            purchaseService.executeMenu(customer);

        } else {
            System.out.println("customer not found ");
            return;
        }
    }
}