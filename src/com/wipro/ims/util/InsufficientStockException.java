package com.wipro.ims.util;

public class InsufficientStockException extends Exception {
    private String productId;
    private int requested;
    private int available;
    
    public InsufficientStockException(String productId, int requested, int available) {
        super("Insufficient stock for product: " + productId);
        this.productId = productId;
        this.requested = requested;
        this.available = available;
    }
    
    @Override
    public String toString() {
        return "InsufficientStockException: Cannot sell " + requested + " units of product '" + 
               productId + "'. Only " + available + " units available.";
    }
}