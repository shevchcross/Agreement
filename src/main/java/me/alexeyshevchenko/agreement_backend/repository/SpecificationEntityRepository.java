package me.alexeyshevchenko.agreement_backend.repository;

import me.alexeyshevchenko.agreement_backend.models.SpecificationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface SpecificationEntityRepository extends PagingAndSortingRepository <SpecificationEntity, Long> {
  Optional<SpecificationEntity> findByPrice(BigDecimal price);
}
