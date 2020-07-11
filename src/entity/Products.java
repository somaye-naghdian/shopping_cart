package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String name;
    private double price;
    private int stock;
    private String brand;
    @ManyToOne
    private ShoppingCart shoppingCart;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public double getPrice() {
        return price;
    }

    public Products() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return
                Double.compare(products.price, price) == 0 &&
                        stock == products.stock &&
                        Objects.equals(category, products.category) &&
                        Objects.equals(name, products.name) &&
                        Objects.equals(brand, products.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, price, stock, brand);
    }

    @Override
    public String toString() {
        return "Products{" +
                " branch='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                '}';
    }
}
