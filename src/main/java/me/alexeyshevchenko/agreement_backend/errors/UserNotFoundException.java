package me.alexeyshevchenko.agreement_backend.errors;

/**
 * Created by ${Aleksey} on 09.08.2018.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
}
}
