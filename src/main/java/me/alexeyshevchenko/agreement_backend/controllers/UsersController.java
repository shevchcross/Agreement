package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.models.UserEntity;
import me.alexeyshevchenko.agreement_backend.services.UsersService;
import me.alexeyshevchenko.agreement_backend.errors.IdException;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import me.alexeyshevchenko.agreement_backend.errors.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by ${Aleksey} on 03.08.2018.
 */
@RequestMapping("/users")
@RestController
public class UsersController {

    @Autowired(required = false)
    private UsersService usersService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping()
    public
    @ResponseBody
    UserDTO createUser(@RequestBody @Valid UserDTO user, BindingResult result) throws LoginPasswordException {
        if (result.hasErrors()) {
            throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
        }
        String password = user.getPassword();
        String salt = UUID.randomUUID().toString();
        String hashedPassword = passwordEncoder.encode(password + salt);
        user.setPassword(hashedPassword);
        UserEntity userToSave = new UserEntity(user);
        userToSave.setSalt(salt);

        UserEntity newUser = usersService.createUser(userToSave);
        return new UserDTO(newUser);
    }

    @GetMapping(value = "/bylogin/{login}", consumes = {"application/json"})
    public
    @ResponseBody
    UserDTO findUserByLogin(@PathVariable("login") String userLogin) throws LoginPasswordException, UserNotFoundException {
        if (!Pattern.matches("[0-9a-zA-Z]{3,30}", userLogin)) {
            throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
        }
        UserEntity userByLogin = usersService.findUserByLogin(userLogin);
        if (userByLogin == null) {
            throw new UserNotFoundException("User not found");
        }
        return new UserDTO(userByLogin);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public
    @ResponseBody
    UserDTO getUserById(@PathVariable("id") Long userId) throws IdException, UserNotFoundException {
        if (userId < 0) {
            throw new IdException("Incorrect Id");
        }
        UserEntity userById = usersService.getUserById(userId);
        if (userById != null && userId == (userById.getId())) {
            return new UserDTO(userById);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = "application/json")
    public
    @ResponseBody
    UserDTO updateUser(@PathVariable("id") Long id,
                       @RequestBody @Valid UserDTO user) throws IdException, UserNotFoundException {
        if (id <= 0) {
            throw new IdException("Incorrect Id");
        }
        UserEntity oldUser = usersService.getUserById(id);
        if (oldUser != null && oldUser.getId() == id) {
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            UserEntity updatedUser = usersService.updateUser(oldUser);
            return new UserDTO(updatedUser);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public
    @ResponseBody
    UserDTO deleteUser(@PathVariable("id") Long userId) throws IdException, UserNotFoundException {
        if (userId <= 0) {
            throw new IdException("Incorrect Id");
        }
        UserEntity userById = usersService.getUserById(userId);
        if (userById == null) {
            throw new UserNotFoundException("User not found");
        }
        return new UserDTO(userById);
    }

    @PostMapping(value = "/auth")
    public
    @ResponseBody
    UserDTO findUserByLoginCheckPassword(@RequestBody @Valid UserDTO user, BindingResult result) throws LoginPasswordException, UserNotFoundException {
        if (result.hasErrors()) {
            throw new LoginPasswordException("Incorrect Login, Please check and try again");
       }
        UserEntity userFromBase = usersService.findUserByLogin(user.getLogin());
        if (userFromBase == null) {
            throw new UserNotFoundException("User not found");
        }
        String passwordFromBase = userFromBase.getPassword();
        String passwordUser = user.getPassword() + userFromBase.getSalt();
        if (passwordEncoder.matches(passwordUser, passwordFromBase)) {
            return new UserDTO(userFromBase);
        } else {
            throw new LoginPasswordException("Incorect password");
        }

    }
}

