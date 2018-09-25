package me.alexeyshevchenko.agreement_backend.dto;


import me.alexeyshevchenko.agreement_backend.models.ProductEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDTO {

    private long id;

    @NotNull
    @Size(min=3, max=45)
    private String name;

    public ProductDTO() {
    }

    public ProductDTO(long id,  String name) {
        this.id = id;
        this.name = name;
    }
    public ProductDTO(String name) {
        this.name = name;
    }
    public ProductDTO(ProductEntity product) {
       id = product.getId();
       name = product.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
