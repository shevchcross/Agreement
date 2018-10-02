package me.alexeyshevchenko.agreement_backend.models;

import me.alexeyshevchenko.agreement_backend.dto.AddressDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MY_ADDRESS_ENTITY")
public class AddressEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID_MY_ADDRESS_ENTITY")
    private long id;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "STREET_MY_ADDRESS_ENTITY")
    private String street;

    @NotNull
    @Size(min=1, max=45)
    @Column(name = "HOME_MY_ADDRESS_ENTITY")
    private String home;

    @NotNull
    @Size(min=1, max=45)
    @Column(name = "APARTMENT_MY_ADDRESS_ENTITY")
    private String apartment;

    @NotNull
    @Size(min=3, max=45)
    @Column(name = "CITY_MY_ADDRESS_ENTITY")
    private String city;

    public AddressEntity() {
    }

    public AddressEntity( String street, String home,  String apartment,  String city) {
        this.street = street;
        this.home = home;
        this.apartment = apartment;
        this.city = city;
    }

    public AddressEntity(AddressDTO addressDTO) {
        this.street = addressDTO.getStreet();
        this.home = addressDTO.getHome();
        this.apartment = addressDTO.getApartment();
        this.city = addressDTO.getCity();
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
