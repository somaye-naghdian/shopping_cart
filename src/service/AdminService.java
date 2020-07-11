package service;

import utility.AgeComparator;
import dao.AdminDao;
import dao.OperationLogDao;
import entity.Customer;
import entity.OperationLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static service.CustomerService.customerDao;

public class AdminService {
    AdminDao adminDao = new AdminDao();

    public void loginAdmin() {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("enter username : ");
            String username = scanner.next();
            System.out.println("enter password : ");
            String password = scanner.next();
            if (adminDao.passwordValidation(username, password) != null) {
                System.out.print("enter your request: 1 :: for sort Customers with ages \n " +
                        "\t\t\t        2 :: for customer activity in month ");
                int adminInput = scanner.nextInt();
                switch (adminInput) {
                    case 1:
                        getCustomerAge();
                    case 2:
                        System.out.println("enter start date with pattern \"yyyy-MM-dd\" ");
                       String startDate= scanner.next();
                        getCustomerActivity(startDate);
                }
            } else {
                System.out.println("invalid Admin ");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("username or password is invalid ");
        }
    }

    public void getCustomerAge() {
        ArrayList<Customer> customers;
        customers = customerDao.getCustomerList();
        Collections.sort(customers, new AgeComparator());
        for (Customer customer :
                customers) {
            System.out.println(customer);
        }
    }

    public void getCustomerActivity(String inputDate) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            c.setTime(sdf.parse(inputDate));
        }catch(ParseException e){
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, 30);
        String endDate = sdf.format(c.getTime());

        OperationLogDao operationLogDao = new OperationLogDao();
        List<OperationLog> operationLogs = operationLogDao.getOperationList(inputDate,endDate);
        for (OperationLog operationLog :
                operationLogs) {
            System.out.print("user: " + operationLog.getAuthority() + " | ");
            System.out.print("operation: " + operationLog.getOperation() + " | ");
            System.out.print("date: " + operationLog.getDate() + " | ");
            System.out.print("time: " + operationLog.getTime() + "\n");
        }
    }
}
