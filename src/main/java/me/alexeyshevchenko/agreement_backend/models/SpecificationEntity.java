package me.alexeyshevchenko.agreement_backend.models;
import me.alexeyshevchenko.agreement_backend.dto.SpecificationDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "MY_SPECIFICATION_ENTITY")
public class SpecificationEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID_MY_SPECIFICATION_ENTITY")
    private long id;

    @NotNull
    @Size(min=2, max=10)
    @Column(name = "MESURE_MY_SPECIFICATION_ENTITY")
    private String mesure;

    @NotNull
    @Column(name = "PRICE_MY_SPECIFICATION_ENTITY")
    private BigDecimal price;


    @NotNull
    @Column(name = "QUANTITY_MY_SPECIFICATION_ENTITY")
    private BigDecimal quantity;

    public SpecificationEntity() {
    }

    public SpecificationEntity(@NotNull @Size(min = 2, max = 10) String mesure, @NotNull BigDecimal price, @NotNull BigDecimal quantity) {
        this.mesure = mesure;
        this.price = price;
        this.quantity = quantity;
    }
    public SpecificationEntity(SpecificationDTO specificationDTO) {
        this.mesure = specificationDTO.getMesure();
        this.price = specificationDTO.getPrice();
        this.quantity = specificationDTO.getQuantity();
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
