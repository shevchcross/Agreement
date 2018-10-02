package me.alexeyshevchenko.agreement_backend.errors;

import java.math.BigDecimal;

public class SpecificationNotFoundException extends Exception {
  public SpecificationNotFoundException(long productId){
    super("Specification with `" + productId + "` doesn't exist");
  }
  public SpecificationNotFoundException(BigDecimal name){
    super("Specification with `" + name + "` doesn't exist");
  }
}
