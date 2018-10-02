package me.alexeyshevchenko.agreement_backend.repository;

import me.alexeyshevchenko.agreement_backend.models.BankEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BankEntityRepository extends PagingAndSortingRepository <BankEntity, Long> {
    Optional<BankEntity> findByMfo(String mfo);
}
