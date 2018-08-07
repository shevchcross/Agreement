package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;
import me.alexeyshevchenko.agreement_backend.Services.UsersService;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new LoginPasswordException("Incorrectc Login or password, Please check and try again");
        }
        return usersService.createUser(user);
    }
}
