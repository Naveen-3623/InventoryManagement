package com.wipro.ims.util;

public class InvalidProductException extends Exception {
    private String productId;
    
    public InvalidProductException(String productId) {
        super("Invalid Product ID: " + productId);
        this.productId = productId;
    }
    
    @Override
    public String toString() {
        return "InvalidProductException: Product with ID '" + productId + "' does not exist in inventory.";
    }
}