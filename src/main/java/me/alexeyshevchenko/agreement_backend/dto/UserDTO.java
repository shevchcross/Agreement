package me.alexeyshevchenko.agreement_backend.dto;

import me.alexeyshevchenko.agreement_backend.models.UserEntity;

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

    private long id;
    @NotNull
    @Size(min=3, max=30)
    private String lastName;
    @NotNull
    @Size(min=3, max=30)
    private String firstName;

    public UserDTO(@NotNull @Size(min = 8, max = 30) String login, @NotNull @Size(min = 6, max = 30) String password, long id, @NotNull @Size(min = 3, max = 30) String lastName, @NotNull @Size(min = 3, max = 30) String firstName) {
        this.login = login;
        this.password = password;
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

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

    public UserDTO(UserEntity user) {
        this(user.getLogin(), null, user.getId(), user.getLastName(), user.getFirstName());
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
