
package com.wipro.ims.service;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.wipro.ims.entity.*;
import com.wipro.ims.util.*;

public class InventoryService {
    private ArrayList<Product> products;
    private ArrayList<InventoryTransaction> transactions;
    private int transactionCounter = 1;
    
    public InventoryService(ArrayList<Product> products, ArrayList<InventoryTransaction> transactions) {
        this.products = products;
        this.transactions = transactions;
    }
    
    public boolean validateProduct(String productId) throws InvalidProductException {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return true;
            }
        }
        throw new InvalidProductException(productId);
    }
    
    public Product getProduct(String productId) throws InvalidProductException {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        throw new InvalidProductException(productId);
    }
    
    public InventoryTransaction purchaseStock(String productId, int qty) 
            throws TransactionException, InvalidProductException {
        
        if (qty <= 0) {
            throw new TransactionException("Quantity must be positive for purchase operation");
        }
        
        validateProduct(productId);
        
        Product product = getProduct(productId);
        
        int newQuantity = product.getQuantityAvailable() + qty;
        product.setQuantityAvailable(newQuantity);
        
        String txnId = "TXN" + String.format("%03d", transactionCounter++);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        InventoryTransaction transaction = new InventoryTransaction(
            txnId, productId, "PURCHASE", qty, currentDate
        );
        
        transactions.add(transaction);
        
        return transaction;
    }
    
    public InventoryTransaction sellStock(String productId, int qty) 
            throws InsufficientStockException, TransactionException, InvalidProductException {
        
        if (qty <= 0) {
            throw new TransactionException("Quantity must be positive for sale operation");
        }
        
        validateProduct(productId);
        
        Product product = getProduct(productId);
        
        if (product.getQuantityAvailable() < qty) {
            throw new InsufficientStockException(productId, qty, product.getQuantityAvailable());
        }
        
        int newQuantity = product.getQuantityAvailable() - qty;
        product.setQuantityAvailable(newQuantity);
        
        String txnId = "TXN" + String.format("%03d", transactionCounter++);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        InventoryTransaction transaction = new InventoryTransaction(
            txnId, productId, "SALE", qty, currentDate
        );
        
        transactions.add(transaction);
        
        return transaction;
    }
    
    public void printStockSummary() {
        System.out.println("Product ID\tProduct Name\t\tCategory\tPrice\t\tStock");
        System.out.println("--------------------------------------------------------------------------");
        for (Product p : products) {
            System.out.printf("%s\t\t%-20s\t%-15s\t%.2f\t\t%d\n", 
                p.getProductId(), 
                p.getProductName(), 
                p.getCategory(), 
                p.getPrice(), 
                p.getQuantityAvailable()
            );
        }
    }
    
    public void printTransactionHistory(String productId) {
        System.out.println("Transaction ID\tType\t\tQuantity\tDate");
        System.out.println("--------------------------------------------------------");
        
        boolean found = false;
        for (InventoryTransaction t : transactions) {
            if (t.getProductId().equals(productId)) {
                System.out.printf("%s\t\t%-12s\t%d\t\t%s\n", 
                    t.getTransactionId(), 
                    t.getTransactionType(), 
                    t.getQuantity(), 
                    t.getDate()
                );
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No transactions found for product: " + productId);
        }
    }
}