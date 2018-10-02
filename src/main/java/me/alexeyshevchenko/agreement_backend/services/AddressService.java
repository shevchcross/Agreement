package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.AddressDTO;
import me.alexeyshevchenko.agreement_backend.errors.AddressNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.AddressEntity;
import me.alexeyshevchenko.agreement_backend.repository.AddressEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressEntityRepository addressEntityRepository;

    public AddressDTO createAddress (AddressDTO address) {
        AddressEntity addressToSave = new AddressEntity(address);
        AddressEntity savedAddress = addressEntityRepository.save(addressToSave);
        return new AddressDTO(savedAddress);
    }

    public AddressDTO getAddressById(Long id) throws AddressNotFoundException {
        Optional<AddressEntity> address = addressEntityRepository.findById(id);
        AddressEntity addressEntity = address.orElseThrow(() -> new AddressNotFoundException(id));
        return new AddressDTO(addressEntity);
    }
    // сделал т.к. нужен только для теста
    public AddressDTO findAddressByStreet(String street) throws AddressNotFoundException {
        Optional<AddressEntity> address = addressEntityRepository.findByStreet(street);
        AddressEntity addressEntity = address.orElseThrow(() -> new AddressNotFoundException(street));
        return new AddressDTO(addressEntity);
    }
    public AddressDTO updateAddress(AddressDTO address) throws AddressNotFoundException {
        AddressEntity oldAddress = addressEntityRepository.findById(address.getId())
                .orElseThrow(() -> new AddressNotFoundException(address.getId()));
        oldAddress.setStreet(address.getStreet());
        oldAddress.setHome(address.getHome());
        oldAddress.setApartment(address.getApartment());
        oldAddress.setCity(address.getCity());
        addressEntityRepository.save(oldAddress);
        return new AddressDTO(oldAddress);
    }
    public AddressDTO deleteAddress(Long id) throws AddressNotFoundException {
        Optional<AddressEntity> existedProduct = addressEntityRepository.findById(id);
        AddressEntity address = existedProduct.orElseThrow(() -> new AddressNotFoundException(id));
        addressEntityRepository.delete(address);
        return new AddressDTO(address);
    }

}
