package com.wipro.ims.entity;

public class Product {
    private String productId;
    private String productName;
    private String category;
    private double price;
    private int quantityAvailable;
    
    public Product(String productId, String productName, String category, double price, int quantityAvailable) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantityAvailable() {
        return quantityAvailable;
    }
    
    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}