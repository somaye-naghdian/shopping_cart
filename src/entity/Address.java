package entity;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String province, city, street;
    private int zipCode;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipCode == address.zipCode &&
                Objects.equals(province, address.province) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
        //  && Objects.equals(customer, address.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, street, zipCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode=" + zipCode +
                ", user=" + user +
                '}';
    }
}
