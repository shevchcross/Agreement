package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.errors.IdException;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import me.alexeyshevchenko.agreement_backend.errors.UserNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.UserEntity;
import me.alexeyshevchenko.agreement_backend.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;


/**
 * Created by ${Aleksey} on 03.08.2018.
 */
@Service
public class UsersService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    public UserEntity createUser(UserEntity user) {
        return userEntityRepository.save(user);
    }

    public UserEntity getUserById(Long id) throws UserNotFoundException {
        return userEntityRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserEntity findUserByLogin(String login) throws LoginPasswordException {
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity updateUser(UserEntity user) throws IdException, UserNotFoundException {
        UserEntity oldUser = getUserById(user.getId());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        userEntityRepository.save(oldUser);
        return oldUser;
    }

    public UserEntity deleteUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> existedUser = userEntityRepository.findById(id);
        UserEntity user = existedUser.orElseThrow(() -> new UserNotFoundException("User not Found"));
        userEntityRepository.delete(user);
        return user;
    }

    ;

}
