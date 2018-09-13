package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.errors.IdException;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import me.alexeyshevchenko.agreement_backend.errors.UserNotFoundException;
import me.alexeyshevchenko.agreement_backend.models.UserEntity;
import me.alexeyshevchenko.agreement_backend.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;


/**
 * Created by ${Aleksey} on 03.08.2018.
 */
@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserEntityRepository userEntityRepository;

    public UserDTO createUser(UserDTO user) {
        String password = user.getPassword();
        String salt = UUID.randomUUID().toString();
        String hashedPassword = passwordEncoder.encode(password + salt);
        user.setPassword(hashedPassword);
        UserEntity userToSave = new UserEntity(user);
        userToSave.setSalt(salt);
        UserEntity savedUser = userEntityRepository.save(userToSave);
        return new UserDTO(savedUser);
    }

    public UserDTO getUserById(Long id) throws UserNotFoundException {
        Optional<UserEntity> user = userEntityRepository.findById(id);
        UserEntity userEntity = user.orElseThrow(() -> new UserNotFoundException("User not found"));
        return new UserDTO(userEntity);
    }

    public UserDTO findUserByLogin(String login) throws LoginPasswordException {
        Optional<UserEntity> user = userEntityRepository.findByLogin(login);
        UserEntity userEntity = user.orElseThrow(() -> new LoginPasswordException("User not found"));
        return new UserDTO(userEntityRepository.findByLogin(login).get());
    }

    public UserDTO updateUser(UserDTO user) throws IdException, UserNotFoundException {
        UserEntity oldUser = userEntityRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        userEntityRepository.save(oldUser);
        return new UserDTO(oldUser);
    }

    public UserDTO deleteUser(Long id) throws UserNotFoundException {
        Optional<UserEntity> existedUser = userEntityRepository.findById(id);
        UserEntity user = existedUser.orElseThrow(() -> new UserNotFoundException(id));
        userEntityRepository.delete(user);
        return new UserDTO(user);
    }
}
