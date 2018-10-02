package me.alexeyshevchenko.agreement_backend.errors;

public class ClienteNotFoundException extends Exception {
  public ClienteNotFoundException(long productId){
    super("Client with `" + productId + "` doesn't exist");
  }
  public ClienteNotFoundException(String name){
    super("Client with `" + name + "` doesn't exist");
  }
}
