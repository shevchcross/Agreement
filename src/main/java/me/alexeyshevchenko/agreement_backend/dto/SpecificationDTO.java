package me.alexeyshevchenko.agreement_backend.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class SpecificationDTO {
    private long id;

    @NotNull
    @Size(min=2, max=10)
    private String mesure;

    @NotNull
    private BigDecimal price;


    @NotNull
    private BigDecimal quantity;

    public SpecificationDTO() {
    }

    public SpecificationDTO(long id, @NotNull @Size(min = 2, max = 10) String mesure, @NotNull BigDecimal price, @NotNull BigDecimal quantity) {
        this.id = id;
        this.mesure = mesure;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMesure() {
        return mesure;
    }

    public void setMesure(String mesure) {
        this.mesure = mesure;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
