package me.alexeyshevchenko.agreement_backend.errors;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(long productId){
        super("Product with `" + productId + "` doesn't exist");
    }
    public ProductNotFoundException(String name){
        super("Product with `" + name + "` doesn't exist");
    }
}
