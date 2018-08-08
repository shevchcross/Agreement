package me.alexeyshevchenko.agreement_backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ${Aleksey} on 03.08.2018.
 */
public class UserDTO {
    @NotNull
    @Size(min=8, max=30)
    private String login;
    @NotNull
    @Size(min=6, max=30)
    private String password;
    private int id;

    public UserDTO(String login, String password, int id) {
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public UserDTO() {
    }

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
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
}
