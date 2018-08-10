package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.Services.UsersService;
import me.alexeyshevchenko.agreement_backend.errors.IdException;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import me.alexeyshevchenko.agreement_backend.errors.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by ${Aleksey} on 03.08.2018.
 */
@RequestMapping("/users")
@RestController
public class UsersController {

    @Autowired(required = false)
    private UsersService usersService;

    @PostMapping()
    public
    @ResponseBody
    UserDTO createUser(@RequestBody @Valid UserDTO user, BindingResult result) throws LoginPasswordException {
        if (result.hasErrors()) {
            throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
        }
        return usersService.createUser(user);
    }

    @PostMapping(value = "/auth", consumes = {"application/json"}, produces = "application/json")
    public
    @ResponseBody
    UserDTO findUserByLogin(@RequestBody @Valid UserDTO user, BindingResult result) throws LoginPasswordException {

        if (result.hasErrors()) {
            throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
        }
        UserDTO userByLogin = usersService.findUserByLogin(user.getLogin());
        if (user.getPassword().equals(userByLogin.getPassword())) {
            return userByLogin;
        } else {
            throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
        }

    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody UserDTO getUserById(@PathVariable("id") int userId) throws IdException, UserNotFoundException {
        if (userId < 0) {
            throw new IdException("Incorrect Id");
        }
        UserDTO userById = usersService.getUserById(userId);
        if (userId == (userById.getId())) {
            return userById;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = "application/json")
    public
    @ResponseBody
    UserDTO updateUser(@PathVariable("id")int id,
                       @RequestBody @Valid UserDTO user) throws IdException, UserNotFoundException {

        if (id<0) {
            throw new IdException("Incorrect Id");
        }
        if (id == user.getId()) {
            UserDTO oldUser = usersService.getUserById(id);
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            return oldUser;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}

