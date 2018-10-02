package me.alexeyshevchenko.agreement_backend.errors;

import java.math.BigDecimal;

public class AgreementNotFoundException extends Exception {
  public AgreementNotFoundException(long productId){
    super("Agreement `" + productId + "` doesn't exist");
  }
  public AgreementNotFoundException(BigDecimal name){
    super("Agreement with `" + name + "` doesn't exist");
  }
}
