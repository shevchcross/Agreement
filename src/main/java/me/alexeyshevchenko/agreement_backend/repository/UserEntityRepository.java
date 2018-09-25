package me.alexeyshevchenko.agreement_backend.repository;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.models.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by ${Aleksey} on 30.08.2018.
 */
@Repository
public interface UserEntityRepository extends PagingAndSortingRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);
}
