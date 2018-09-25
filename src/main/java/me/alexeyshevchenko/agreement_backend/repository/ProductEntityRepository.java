package me.alexeyshevchenko.agreement_backend.repository;

import me.alexeyshevchenko.agreement_backend.models.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductEntityRepository extends PagingAndSortingRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);
}
