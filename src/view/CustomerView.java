package view;

import dao.UserDao;
import entity.Address;
import entity.User;
import service.CustomerService;
import service.ShoppingCartService;
import sun.plugin.dom.exception.InvalidAccessException;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerView {
    CustomerService customerService = new CustomerService();
    static ShoppingCartService shoppingCartService = new ShoppingCartService();

    Scanner scanner = new Scanner(System.in);

    public void loginCustomer() {

        System.out.println("enter username : ");
        String username = scanner.next();
        System.out.println("enter password : ");
        String password = scanner.next();
       customerService.signIn(username,password);
    }

    public User customerRegister() {

        UserDao userDao = new UserDao();

        System.out.println("enter  name");
        String name = scanner.nextLine();
        System.out.println("enter  family");
        String family = scanner.nextLine();
        System.out.println("enter  age");
        int age = scanner.nextInt();
        System.out.println("enter  phone number with pattern xxx-xxxxxxx");
        String phone = scanner.next();
        if (!phoneValidation(phone)) {
            throw new IllegalArgumentException("your phone pattern is invalid");
        }
        System.out.println("enter  email");
        String email = scanner.next();
        if (!checkEmail(email)) {
            throw new IllegalArgumentException(" your email is invalid ");
        }
        System.out.println("enter  address->province");
        String province = scanner.next();
        System.out.println("enter  address->city");
        String city = scanner.next();
        System.out.println("enter  address->street");
        String street = scanner.next();
        System.out.println("enter  address->postalCode");
        int zipCode = scanner.nextInt();
        Address address = new Address();
        address.setProvince(province);
        address.setCity(city);
        address.setStreet(street);
        address.setZipCode(zipCode);
        System.out.println("enter a username Including at least 8 character ");
        String username = scanner.next();
        if (!usernameValidation(username)) {
            throw new IllegalArgumentException("username must include characters and numbers");
        }
        if (userDao.searchDuplicateUserName(username)) {
            System.out.println("enter password Including at least 8 character and les than 10");
            String password = scanner.next();
            if (!passwordValidation(password)) {
                throw new IllegalArgumentException("password is not safe");
            }
            if (username.equals(password)) {
                throw new IllegalArgumentException("Username and password must be different");
            }
            User customer = new User();
            customer.setName(name);
            customer.setFamily(family);
            customer.setAge(age);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setUserName(username);
            customer.setPassword(password);
            customer.setAddress(address);
            address.setUser(customer);
            customerService.signUpUser(customer);
            return customer;
        } else {
            throw new InvalidAccessException("username is previously selected ");
        }
    }

    public boolean phoneValidation(String phone) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean checkEmail(String email) {
        char ch = '@';
        char ch2 = '.';
        int count = 0;
        int count2 = 0;
        if (email.charAt(0) == ch || email.charAt(0) == ch2) {
            return false;
        }
        for (int i = 0; i < email.length(); i++) {
            if (Objects.equals(email.charAt(i), ch))
                count++;
            if (Objects.equals(email.charAt(i), ch2))
                count2++;
        }
        if (count == 0 || count2 == 0) {
            return false;
        }
        return true;
    }

    public boolean passwordValidation(String password) {
        if (password.length() < 8 || password.length() > 10) {
            return false;
        }
        return true;
    }

    public boolean usernameValidation(String username) {
        if (username.length() < 8 || username.length() > 20) {
            return false;
        }
        String regex = "^[aA-zZ]\\w{5,29}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

}
