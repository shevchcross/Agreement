package me.alexeyshevchenko.agreement_backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MY_CLIENT_ENTITY")
public class ClientEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID_MY_CLIENT_ENTITY")
    private long id;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "NAME_MY_CLIENT_ENTITY")
    private String name;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "EDRPOU_MY_CLIENT_ENTITY")
    private String edrpou;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "DIRECTOR_MY_CLIENT_ENTITY")
    private String director;

    public ClientEntity() {
    }

    public ClientEntity(@NotNull @Size(min = 3, max = 45) String name, @NotNull @Size(min = 3, max = 45) String edrpou, @NotNull @Size(min = 3, max = 45) String director) {
        this.name = name;
        this.edrpou = edrpou;
        this.director = director;
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
}
