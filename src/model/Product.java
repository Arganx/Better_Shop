package model;

import javafx.beans.property.StringProperty;

/**
 * Created by qwerty on 19-Mar-17.
 */
public class Product {
    private String name;
    private int amount;
    private ProductType type;
    private boolean available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Product(String name, int amount, ProductType type, boolean available) {
        this.name=name;
        this.amount = amount;
        this.type = type;
        this.available = available;
    }

    public Product() {
    }

}
