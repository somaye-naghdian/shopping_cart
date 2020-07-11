package utility;

import entity.Customer;

import java.util.Comparator;

public class AgeComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer customer1, Customer customer2) {
        return customer1.getAge() == customer2.getAge() ? 0 : customer1.getAge() > customer2.getAge() ? 1 : -1;
    }
}
