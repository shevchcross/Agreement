package me.alexeyshevchenko.agreement_backend.errors;

public class BankNotFoundException extends Exception {
    public BankNotFoundException(long productId){
        super("Bank with `" + productId + "` doesn't exist");
    }
    public BankNotFoundException(String name){
        super("Bank with `" + name + "` doesn't exist");
    }
}
