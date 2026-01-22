package com.wipro.ims.main;
import java.util.ArrayList;
import com.wipro.ims.entity.*;
import com.wipro.ims.service.InventoryService;
import com.wipro.ims.util.*;
public class Main {
 public static void main(String[] args) {
 ArrayList<Product> products = new ArrayList<>();
 products.add(new Product("P001", "Laptop", "Electronics", 55000.0, 20));
 products.add(new Product("P002", "Wireless Mouse", "Electronics", 700.0, 50));
 products.add(new Product("P003", "Rice Bag 25kg", "Grocery", 1200.0, 10));
 ArrayList<InventoryTransaction> transactions = new ArrayList<>();
 InventoryService service = new InventoryService(products, transactions);
 try {
 // Add stock
 InventoryTransaction t1 = service.purchaseStock("P001", 5);
 System.out.println("Stock added successfully. Transaction ID: " + t1.getTransactionId());
 // Sell stock
 InventoryTransaction t2 = service.sellStock("P003", 3);
 System.out.println("Stock sold successfully. Transaction ID: " + t2.getTransactionId());
//Print summaries
System.out.println("\n--- Stock Summary ---");
service.printStockSummary();
System.out.println("\n--- Transaction History for P001 ---");
service.printTransactionHistory("P001");
} catch (InvalidProductException ipe) {
System.out.println(ipe.toString());
} catch (InsufficientStockException ise) {
System.out.println(ise.toString());
} catch (TransactionException te) {
System.out.println(te.toString());
} catch (Exception ex) {
System.out.println("Unexpected Error: " + ex);
}
}
}