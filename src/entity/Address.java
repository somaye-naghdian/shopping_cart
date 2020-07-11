package entity;

import java.util.Objects;

public class Address {
    private String province, city, street;
    private int zipCode;
    Customer customer;

    public Address(String province, String city, String street, int zipCode) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipCode == address.zipCode &&
                Objects.equals(province, address.province) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(customer, address.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, street, zipCode, customer);
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode=" + zipCode +
                ", customer=" + customer +
                '}';
    }
}
