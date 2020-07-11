package entity;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private int id;
    private String name;
    private String family;
    private int age;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private Address address;

    public Customer(String name, String family, int age, String userName, String password, String phone, String email) {
        try {

            if (!phoneValidation(phone)) {
                throw new IllegalArgumentException("your phone pattern is invalid");
            }
            if (!checkEmail(email)) {
                throw new IllegalArgumentException(" your email is invalid ");
            }
            if (userName.length() < 8 || userName.length() > 20) {
                throw new IllegalArgumentException("username is not too short or too long");
            }
            if (password.length() < 8 || password.length() > 10) {
                throw new IllegalArgumentException("password is not safe");
            }
            this.name = name;
            this.family = family;
            this.age = age;
            this.userName = userName;
            this.password = password;
            this.phone = phone;
            this.email = email;

        } catch (InputMismatchException e) {
            System.out.println("invalid input");
        }
    }

    public static boolean phoneValidation(String phone) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkEmail(String email) {
        char ch = '@';
        char ch2 = '.';
        int count = 0;
        int count2 = 0;
        for (int i = 0; i < email.length(); i++) {
            if (Objects.equals(email.charAt(i), ch))
                count++;
            if (Objects.equals(email.charAt(i), ch2))
                count2++;
        }
        if (count == 0 && count2 == 0) {
            return false;
        }
        return true;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName.length() < 8 || userName.length() > 20) {
            throw new IllegalArgumentException("username is not too short or too long");
        }
        this.userName = userName;
    }

    public Customer() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 8 || password.length() > 10) {
            throw new IllegalArgumentException("password is not safe");
        }
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!phoneValidation(phone)) {
            throw new IllegalArgumentException("your phone pattern is invalid");
        }
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!checkEmail(email)) {
            throw new IllegalArgumentException(" your email is invalid ");
        }
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                age == customer.age &&
                Objects.equals(name, customer.name) &&
                Objects.equals(family, customer.family) &&
                Objects.equals(userName, customer.userName) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, family, age, userName, password, phone, email, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
