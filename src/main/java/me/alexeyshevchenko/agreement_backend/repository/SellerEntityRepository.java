package me.alexeyshevchenko.agreement_backend.repository;

import me.alexeyshevchenko.agreement_backend.models.SellerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerEntityRepository extends PagingAndSortingRepository <SellerEntity, Long> {
  Optional<SellerEntity> findByedrpou(String edrpou);
}
