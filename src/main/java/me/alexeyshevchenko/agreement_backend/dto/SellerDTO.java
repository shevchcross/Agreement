package me.alexeyshevchenko.agreement_backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SellerDTO {
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

    @NotNull
    private boolean nds;

    public SellerDTO() {
    }

    public SellerDTO(long id, @NotNull @Size(min = 3, max = 45) String name, @NotNull @Size(min = 3, max = 45) String edrpou, @NotNull @Size(min = 3, max = 45) String director, @NotNull boolean nds) {
        this.id = id;
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
