package service;

import dao.CustomerDao;
import entity.Address;
import entity.Customer;
import sun.plugin.dom.exception.InvalidAccessException;

import java.util.Scanner;

public class CustomerService {
    static CustomerDao customerDao = new CustomerDao();
    static ProductService productService = new ProductService();

    public void loginCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter username : ");
        String username = scanner.next();
        System.out.println("enter password : ");
        String password = scanner.next();
        Customer customer = customerDao.passwordValidation(username, password);
        if (customerDao.passwordValidation(username, password) != null) {
            System.out.println("successful login");
            productService.addActivity(customer, OperationsType.LOGIN);
            productService.executeMenu(customer);
        } else {
            System.out.println("customer not fond ");
            return;
        }
    }

    public Customer customerRegister() {
        Scanner scanner = new Scanner(System.in);
        CustomerDao customerDao = new CustomerDao();
        System.out.println("enter  name");
        String name = scanner.nextLine();
        System.out.println("enter  family");
        String family = scanner.nextLine();
        System.out.println("enter  age");
        int age = scanner.nextInt();
        System.out.println("enter  phone number with pattern xxx-xxxxxxx");
        String phone = scanner.next();
        System.out.println("enter  email");
        String email = scanner.next();
        System.out.println("enter  address->province");
        String province = scanner.next();
        System.out.println("enter  address->city");
        String city = scanner.next();
        System.out.println("enter  address->street");
        String street = scanner.next();
        System.out.println("enter  address->postalCode");
        int zipCode = scanner.nextInt();
        Address address = new Address(province, city, street, zipCode);
        System.out.println("enter a username Including at least 8 character ");
        String username = scanner.next();
        if (customerDao.searchDuplicateUserName(username)) {
            System.out.println("enter password Including at least 8 character and les than 10");
            String password = scanner.next();
            Customer customer = new Customer(name, family, age, username, password, phone, email);
            customer.setAddress(address);
            customerDao.insertCustomer(customer);

            productService.addActivity(customer, OperationsType.REGISTER);
            return customer;
        } else {
            throw new InvalidAccessException("username is previously selected ");
        }
    }
}