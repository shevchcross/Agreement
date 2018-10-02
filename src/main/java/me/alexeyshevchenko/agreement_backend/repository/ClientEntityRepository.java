package me.alexeyshevchenko.agreement_backend.repository;

import me.alexeyshevchenko.agreement_backend.models.ClientEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientEntityRepository extends PagingAndSortingRepository<ClientEntity, Long> {
  Optional<ClientEntity> findByedrpou(String edrpou);
}
