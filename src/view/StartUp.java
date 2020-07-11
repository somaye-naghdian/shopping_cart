package view;

import entity.User;
import service.AdminService;
import service.ShoppingCartService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StartUp {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        CustomerView customerView = new CustomerView();
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        AdminView adminView = new AdminView();
        AdminService adminService =new AdminService();
        int input = 0;
        System.out.print("  Log in     - press 1" + "\n" + "new customer - press 2\n" +
                "\tAdmin    - press 3\n");
        try {
            input = scanner.nextInt();
            switch (input) {
                case 1: {
                    customerView.loginCustomer();
                    break;
                }
                case 2: {
                    User customer = customerView.customerRegister();
                    shoppingCartService.executeMenu(customer);
                    break;
                }
                case 3: {
                    adminService.createAdmin();
                    adminView.loginAdmin();
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("your input : " + input + " is invalid");
        }
    }
}

