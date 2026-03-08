package com.wms.model;

/**
 * Class Product mewarisi BaseEntity (Inheritance).
 * Menerapkan enkapsulasi dengan member variables private.
 */
public class Product extends BaseEntity {
    private String name;
    private String category;
    private int quantity;
    private double price;

    public Product() {
        super();
    }

    // Overloading Constructor
    public Product(int id, String name, String category, int quantity, double price) {
        super(id);
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    // Properties / Encapsulation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Polimorfisme (Overriding / Implementasi abstract method)
    @Override
    public String getDetails() {
        return String.format("Product [ID=%d, Name=%s, Category=%s, Qty=%d, Price=%.2f]", 
            getId(), name, category, quantity, price);
    }
}
