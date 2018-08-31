package me.alexeyshevchenko.agreement_backend.repository;

import me.alexeyshevchenko.agreement_backend.models.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ${Aleksey} on 30.08.2018.
 */
@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);
}
