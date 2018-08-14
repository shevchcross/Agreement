package me.alexeyshevchenko.agreement_backend.services;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;

/**
 * Created by ${Aleksey} on 03.08.2018.
 */
public interface UsersService {

    UserDTO createUser(UserDTO user);
    UserDTO getUserById(int id);
    UserDTO findUserByLogin(String login);
    UserDTO updateUser(int id, String login, String passworld);
    UserDTO deleteUser(int id);
    UserDTO createUserSafery (UserDTO user);


}
