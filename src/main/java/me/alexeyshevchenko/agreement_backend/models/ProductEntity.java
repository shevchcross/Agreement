package me.alexeyshevchenko.agreement_backend.models;

import me.alexeyshevchenko.agreement_backend.dto.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MY_PRODUCT_ENTITY")
public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID_MY_PRODUCT_ENTITY")
    private long id;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "NAME_MY_PRODUCT_ENTITY")
    private String name;

    public ProductEntity() {
    }
    public ProductEntity(ProductDTO product) {
        id= product.getId();
        name = product.getName();
    }

    public ProductEntity(@NotNull @Size(min = 3, max = 45) String name) {
        this.name = name;
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
