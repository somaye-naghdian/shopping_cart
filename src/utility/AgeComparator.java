package utility;

import entity.User;

import java.util.Comparator;

public class AgeComparator implements Comparator<User> {
    @Override
    public int compare(User customer1, User customer2) {
        return customer1.getAge() == customer2.getAge() ? 0 : customer1.getAge() > customer2.getAge() ? 1 : -1;
    }
}
