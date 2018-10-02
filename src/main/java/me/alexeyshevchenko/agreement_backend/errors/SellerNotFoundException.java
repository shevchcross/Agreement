package me.alexeyshevchenko.agreement_backend.errors;

public class SellerNotFoundException extends Exception {
  public SellerNotFoundException(long productId){
    super("Seller with `" + productId + "` doesn't exist");
  }
  public SellerNotFoundException(String name){
    super("Seller with `" + name + "` doesn't exist");
  }
}
