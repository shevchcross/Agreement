package me.alexeyshevchenko.agreement_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.alexeyshevchenko.agreement_backend.dto.UserDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ${Aleksey} on 15.08.2018.
 */
public class UserEntity {
    private int id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String salt;

    public UserEntity() {
    }

    public UserEntity(UserDTO user) {
        login = user.getLogin();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
