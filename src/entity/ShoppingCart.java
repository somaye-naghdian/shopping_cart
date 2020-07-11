package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ShoppingCart {

    public ShoppingCart() {
        capacity = 5;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private int itemCount;
    private double totalPrice;
    private int capacity;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    List<Products> productList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return 5;
    }

    public void setCapacity() {
        this.capacity = 5;
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
                Objects.equals(user, that.user) &&
                Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCount, totalPrice, capacity, productList);
    }

    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "user=" + user +
                ", itemCount=" + itemCount +
                ", totalPrice=" + totalPrice +
                ", capacity=" + capacity +
                ", productList=" + productList +
                '}';
    }


}
