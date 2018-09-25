package me.alexeyshevchenko.agreement_backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressDTO {
     private long id;

    @NotNull
    @Size(min=3, max=45)
    private String street;

    @NotNull
    @Size(min=3, max=45)
    private String home;

    @NotNull
    @Size(min=3, max=45)
    private String apartment;

    @NotNull
    @Size(min=3, max=45)
    private String city;

    public AddressDTO() {
    }

    public AddressDTO(long id, @NotNull @Size(min = 3, max = 45) String street, @NotNull @Size(min = 3, max = 45) String home, @NotNull @Size(min = 3, max = 45) String apartment, @NotNull @Size(min = 3, max = 45) String city) {
        this.id = id;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
