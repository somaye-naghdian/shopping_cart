package entity;

import java.util.Objects;

public class Products {
    private int productId;
    private String category;
    private String name;
    private double price;
    private int stock;
    private String brand;

    public Products(int productId, String branch, String name, double price, int stock, String brand) {
        this.productId = productId;
        this.category = branch;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.brand = brand
        ;
    }

    public Products() {
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
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
        return productId == products.productId &&
                Double.compare(products.price, price) == 0 &&
                stock == products.stock &&
                Objects.equals(category, products.category) &&
                Objects.equals(name, products.name) &&
                Objects.equals(brand, products.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, category, name, price, stock, brand);
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", branch='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                '}';
    }
}
