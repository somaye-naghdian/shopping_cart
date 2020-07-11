package utility;

import entity.Products;

import java.util.Comparator;

public class PriceComparator implements Comparator<Products> {
    @Override
    public int compare(Products product1, Products product2) {
        return product1.getPrice() == product2.getPrice() ? 0 : product1.getPrice() > product2.getPrice() ? 1 : -1;
    }
}
