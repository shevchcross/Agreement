package me.alexeyshevchenko.agreement_backend.errors;

public class AddressNotFoundException extends Exception {
    public AddressNotFoundException(long productId){
        super("Address with `" + productId + "` doesn't exist");
    }
    public AddressNotFoundException(String name){
        super("Address with `" + name + "` doesn't exist");
    }
}
