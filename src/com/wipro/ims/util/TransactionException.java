package com.wipro.ims.util;

public class TransactionException extends Exception {
    private String message;
    
    public TransactionException(String msg) {
        super(msg);
        this.message = msg;
    }
    
    @Override
    public String toString() {
        return "TransactionException: " + message;
    }
}