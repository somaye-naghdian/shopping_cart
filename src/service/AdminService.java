package service;

import dao.OperationLogDao;
import dao.UserDao;
import entity.Address;
import entity.OperationLog;
import entity.User;
import utility.AgeComparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AdminService {
    UserDao userDao = new UserDao();
    ArrayList<User> adminList = new ArrayList<>();

    public boolean validate(User admin) {
        System.out.println(adminList);
        if (adminList.contains(admin)) {
            return true;
        }
        return false;
    }

    public void getCustomerAge() {
        ArrayList<User> customers;
        customers = userDao.getUserList();
        Collections.sort(customers, new AgeComparator());
        for (User customer :
                customers) {
            System.out.println(customer);
        }
    }

    public void getCustomerActivity(String inputDate) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(sdf.parse(inputDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, 30);
        String endDate = sdf.format(c.getTime());

        OperationLogDao operationLogDao = new OperationLogDao();
        List<OperationLog> operationLogs = operationLogDao.getOperationList(inputDate, endDate);
        for (OperationLog operationLog :
                operationLogs) {
            System.out.print("user: " + operationLog.getAuthority() + " | ");
            System.out.print("operation: " + operationLog.getOperation() + " | ");
            System.out.print("date: " + operationLog.getDate() + " | ");

        }
    }

    public void createAdmin() {

        User admin1 = new User();
        admin1.setName("admin1");
        admin1.setFamily("admin1");
        admin1.setEmail("admin1@gmail.com");
        admin1.setAge(40);
        admin1.setPhone("125-1458790");
        admin1.setUserName("admin12345");
        admin1.setPassword("admin12345");
        String province = "Tehran";
        String city = "Tehran";
        String street = "jamejam";
        int postalCode = 1234;
        Address adminAddress = new Address();
        adminAddress.setProvince(province);
        adminAddress.setCity(city);
        adminAddress.setStreet(street);
        adminAddress.setZipCode(postalCode);
        admin1.setAddress(adminAddress);
        userDao.insertUser(admin1);
        adminList.add(admin1);
    }


}
