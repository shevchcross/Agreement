package me.alexeyshevchenko.agreement_backend.repository;


import me.alexeyshevchenko.agreement_backend.models.AgreementEntity;
import me.alexeyshevchenko.agreement_backend.models.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementEntityRepository extends PagingAndSortingRepository<AgreementEntity, Long> {
}
