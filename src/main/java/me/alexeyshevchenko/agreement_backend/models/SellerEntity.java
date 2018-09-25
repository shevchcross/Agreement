package me.alexeyshevchenko.agreement_backend.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MY_SELLER_ENTITY")
public class SellerEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID_MY_SELLER_ENTITY")
    private long id;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "NAME_MY_SELLER_ENTITY")
    private String name;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "EDRPOU_MY_SELLER_ENTITY")
    private String edrpou;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "DIRECTOR_MY_SELLER_ENTITY")
    private String director;

    @NotNull
    @Column(name = "NDS_MY_SELLER_ENTITY")
    private boolean nds;

    public SellerEntity() {
    }

    public SellerEntity(@NotNull @Size(min = 3, max = 45) String name, @NotNull @Size(min = 3, max = 45) String edrpou, @NotNull @Size(min = 3, max = 45) String director, @NotNull boolean nds) {
        this.name = name;
        this.edrpou = edrpou;
        this.director = director;
        this.nds = nds;
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

    public String getEdrpou() {
        return edrpou;
    }

    public void setEdrpou(String edrpou) {
        this.edrpou = edrpou;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public boolean isNds() {
        return nds;
    }

    public void setNds(boolean nds) {
        this.nds = nds;
    }
}
