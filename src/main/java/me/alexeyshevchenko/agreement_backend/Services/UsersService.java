package me.alexeyshevchenko.agreement_backend.Services;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by ${Aleksey} on 03.08.2018.
 */
public interface UsersService {

    UserDTO createUser(UserDTO user);
    UserDTO getUserById(int id);
    UserDTO findUserByLogin(String login);
    UserDTO updateUser(int id, String login, String passworld);
    UserDTO deleteUser(int id);


}
