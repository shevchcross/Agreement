package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.Services.UsersService;
import me.alexeyshevchenko.agreement_backend.errors.IdException;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ${Aleksey} on 03.08.2018.
 */
@RequestMapping("/users")
@RestController
public class UsersController {

    @Autowired(required = false)
    private UsersService usersService;

    @PostMapping()
    public @ResponseBody UserDTO createUser (@RequestBody @Valid UserDTO user, BindingResult result) throws LoginPasswordException {
        if (result.hasErrors()){
            throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
        }
        return usersService.createUser(user);
    }

    @PostMapping(value = "/auth", consumes = {"application/json"}, produces = "application/json")
    public @ResponseBody UserDTO findUserByLogin (@RequestBody @Valid UserDTO user, BindingResult result) throws LoginPasswordException {

        if (result.hasErrors()){
            throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
        }
        UserDTO userByLogin = usersService.findUserByLogin(user.getLogin());
        if(user.getPassword().equals(userByLogin.getPassword())){
            return userByLogin;
        } else {
            throw new LoginPasswordException("Incorrect Login or password, Please check and try again");
        }

    }

    @PostMapping(value = "/get", consumes = {"application/json"}, produces = "application/json")
    public @ResponseBody UserDTO getUserById (@RequestBody @Valid UserDTO user, BindingResult result) throws IdException {

        if (result.hasErrors()) {
            throw new IdException("Incorrect Id");
        }
        UserDTO userById =  usersService.getUserById(user.getId());
        if (user.getId()==(userById.getId())){
            return userById;
        }else {
            throw new IdException("Incorrect Id");
        }
    }
}
