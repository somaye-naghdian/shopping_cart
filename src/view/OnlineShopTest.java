package view;

import dao.CustomerDao;
import entity.Customer;
import service.AdminService;
import service.CustomerService;
import service.ProductService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineShopTest {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        AdminService adminService = new AdminService();
        int input = 0;
        System.out.print("  Log in     - press 1" + "\n" + "new customer - press 2\n" +
                "\tAdmin    - press 3\n");
        try {
            input = scanner.nextInt();
            switch (input) {
                case 1: {
                    customerService.loginCustomer();
                    break;
                }
                case 2: {
                    Customer customer = customerService.customerRegister();
                    productService.executeMenu(customer);
                    break;
                }
                case 3: {
                    adminService.loginAdmin();
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("your input : " + input + " is invalid");
        }
    }
}
