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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.regex.Pattern;

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

    @GetMapping(value = "/bylogin/{login}", consumes = {"application/json"})
    public
    @ResponseBody
    UserDTO findUserByLogin( @PathVariable("login") String userLogin) throws LoginPasswordException, UserNotFoundException {
             if (!Pattern.matches("[0-9a-zA-Z]{3,30}", userLogin)){
             throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
         }
        UserDTO userByLogin = usersService.findUserByLogin(userLogin);
         if(userByLogin == null){
             throw new UserNotFoundException("User not found");
         }
         return userByLogin;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody UserDTO getUserById(@PathVariable("id") int userId) throws IdException, UserNotFoundException {
        if (userId < 0){
            throw new IdException("Incorrect Id");}
        UserDTO userById = usersService.getUserById(userId);
         if (userById != null && userId == (userById.getId())) {
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
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    UserDTO deleteUser(@PathVariable("id") int userId) throws IdException, UserNotFoundException {

        UserDTO userById = usersService.getUserById(userId);
        if (userById != null && userId == (userById.getId())) {
            return userById;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}

