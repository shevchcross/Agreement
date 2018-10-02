package me.alexeyshevchenko.agreement_backend.Service.user_service;

import me.alexeyshevchenko.agreement_backend.dto.AddressDTO;
import me.alexeyshevchenko.agreement_backend.errors.AddressNotFoundException;
import me.alexeyshevchenko.agreement_backend.repository.AddressEntityRepository;
import me.alexeyshevchenko.agreement_backend.services.AddressService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressEntityRepository addressEntityRepository;

    @BeforeEach
    public void init() {
        addressEntityRepository.deleteAll();
    }

    @AfterEach
    public void after() {
        addressEntityRepository.deleteAll();
    }

    @Test
    public void createAddressSuccessfull() throws Exception {
        AddressDTO addressInput = addressService.createAddress(new AddressDTO( "Nauki", "58", "75", "Kharkiv"));
        AddressDTO addressFomeBase = addressService.findAddressByStreet("Nauki");
        Long a = addressFomeBase.getId();
        assertThat(a, greaterThan(0L));
    }

    @Test
    public void getAddressById() throws Exception {
        AddressDTO addressInput = addressService.createAddress(new AddressDTO( "Nauki", "58", "75", "Kharkiv"));
        AddressDTO addressFomeBase = addressService.getAddressById(addressInput.getId());

        assertThat(addressFomeBase.getStreet(), equalToIgnoringCase(addressInput.getStreet()));
        assertThat(addressFomeBase.getApartment(), equalToIgnoringCase(addressInput.getApartment()));
    }

    @Test
    public void getUserByIdAddressNotFound() throws Exception {
        AddressDTO addressInput = addressService.createAddress(new AddressDTO( "Nauki", "58", "75", "Kharkiv"));
        assertThrows(AddressNotFoundException.class,
                () -> {
                    addressService.getAddressById(addressInput.getId() + 1);
                });
    }


    @Test
    public void updateProductSuccessfull() throws Exception {
        AddressDTO addressFromDB = addressService.createAddress(new AddressDTO( "Nauki", "58", "75", "Kharkiv"));

        AddressDTO addressUpdate = addressService.updateAddress(new AddressDTO( addressFromDB.getId(), "23 Avgusta", "55", "77", "Kyiv"));;
        AddressDTO newAddress = addressService.getAddressById(addressFromDB.getId());
        assertThat(newAddress.getStreet(), equalToIgnoringCase(addressUpdate.getStreet()));
        assertThat(newAddress.getApartment(), equalToIgnoringCase(addressUpdate.getApartment()));
        assertThat(newAddress.getCity(), equalToIgnoringCase(addressUpdate.getCity()));
        assertThat(newAddress.getHome(), equalToIgnoringCase(addressUpdate.getHome()));

    }

    @Test
    public void updateAddressNotFound() throws Exception {
        AddressDTO addressFromDB  =addressService.createAddress(new AddressDTO( "Nauki", "58", "75", "Kharkiv"));
        AddressDTO addressUpdate = new AddressDTO(addressFromDB.getId() + 1, "23 Avgusta", "55", "77", "Kyiv");

        assertThrows(AddressNotFoundException.class,
                () -> {
                    addressService.updateAddress(addressUpdate);
                });
    }
    @Test
    public void deleteProductNotFound() throws Exception{
        AddressDTO addressFromDB  =addressService.createAddress(new AddressDTO( "Nauki", "58", "75", "Kharkiv"));
        assertThrows(AddressNotFoundException.class,
                ()->{
                    addressService.deleteAddress(addressFromDB.getId()+1);
                });
    }

}

