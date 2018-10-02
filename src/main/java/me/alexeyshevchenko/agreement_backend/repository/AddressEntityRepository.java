package me.alexeyshevchenko.agreement_backend.repository;

import me.alexeyshevchenko.agreement_backend.models.AddressEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressEntityRepository extends PagingAndSortingRepository<AddressEntity, Long> {
    Optional<AddressEntity> findByStreet(String name);
}
