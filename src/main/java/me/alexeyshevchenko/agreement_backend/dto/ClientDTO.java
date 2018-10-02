package me.alexeyshevchenko.agreement_backend.dto;

import me.alexeyshevchenko.agreement_backend.models.ClientEntity;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientDTO {
    private long id;

    @NotNull
    @Size(min=3, max=45)
    private String name;

    @NotNull
    @Size(min=3, max=45)
    private String edrpou;

    @NotNull
    @Size(min=3, max=45)
    private String director;

    public ClientDTO() {
    }

    public ClientDTO(long id, @NotNull @Size(min = 3, max = 45) String name, @NotNull @Size(min = 3, max = 45) String edrpou, @NotNull @Size(min = 3, max = 45) String director) {
        this.id = id;
        this.name = name;
        this.edrpou = edrpou;
        this.director = director;
    }
    public ClientDTO(ClientEntity clientEntity) {
        this.id = clientEntity.getId();
        this.name = clientEntity.getName();
        this.edrpou = clientEntity.getEdrpou();
        this.director = clientEntity.getDirector();
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
