package me.alexeyshevchenko.agreement_backend.models;

import me.alexeyshevchenko.agreement_backend.dto.UserDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ${Aleksey} on 15.08.2018.
 */
@Entity
@Table(name = "MY_USER_ENTITY")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID_MY_USER_ENTITY")
    private long id;

    @NotNull
    @Size(min=8, max=30)
    @Column(name = "LOGIN_MY_USER_ENTITY")
    private String login;
    @NotNull
    @Size(min=6, max=30)
    @Column(name = "PASSWORD_MY_USER_ENTITY")
    private String password;
    @NotNull
    @Size(min=3, max=30)
    @Column(name = "LAST_NAME_MY_USER_ENTITY")
    private String lastName;
    @NotNull
    @Size(min=3, max=30)
    @Column(name = "FIRST_NAME_MY_USER_ENTITY")
    private String firstName;
    @NotNull
    @Column(name = "SALT_MY_USER_ENTITY")
    private String salt;

    public UserEntity() {
    }

    public UserEntity(UserDTO user) {
        login = user.getLogin();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
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
