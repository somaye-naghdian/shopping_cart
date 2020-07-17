package view;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.AdminService;
import service.PurchaseService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Startup {
    @Autowired
    private CustomerView customerView;
    @Autowired
    private AdminView adminView;
    @Autowired
    private PurchaseService purchaseService;

    public void start() {
        Scanner scanner = new Scanner(System.in);

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
                    purchaseService.executeMenu(customer);
                    break;
                }
                case 3: {

                    adminView.loginAdmin();
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("your input : " + input + " is invalid");
        }
    }
}

