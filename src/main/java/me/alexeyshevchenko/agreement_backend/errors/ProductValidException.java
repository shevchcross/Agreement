package me.alexeyshevchenko.agreement_backend.errors;

public class ProductValidException extends Exception {
    public ProductValidException(String name) {
        super(("Name of product `" + name + "` must be from 3 to 45 symbol"));
    }
}
