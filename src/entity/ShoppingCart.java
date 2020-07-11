package entity;

import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private  static ShoppingCart singletonInstance = new ShoppingCart();
    public static ShoppingCart getInstance(){
        return  singletonInstance;
    }
    public ShoppingCart() {
        capacity = 5;
    }


    private Customer customer;
    private int itemCount;
    private double totalPrice;
    private int capacity;
     List<Products> productList;

    public ShoppingCart(Customer customer, int itemCount) {
        this.customer = customer;
        this.itemCount = itemCount;
        capacity = 5;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Products> getProductList() {
        return productList;
    }

    public void setProductList(List<Products> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return itemCount == that.itemCount &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                capacity == that.capacity &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, itemCount, totalPrice, capacity, productList);
    }

    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "customer=" + customer +
                ", itemCount=" + itemCount +
                ", totalPrice=" + totalPrice +
                ", capacity=" + capacity +
                ", productList=" + productList +
                '}';
    }


}
