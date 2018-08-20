package me.alexeyshevchenko.agreement_backend.services;
import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.models.UserEntity;


/**
 * Created by ${Aleksey} on 03.08.2018.
 */
public interface UsersService {

    UserEntity createUser(UserEntity user);
    UserEntity getUserById(int id);
    UserEntity findUserByLogin(String login);
    UserEntity updateUser(UserEntity user);
    UserEntity deleteUser(int id);

}
