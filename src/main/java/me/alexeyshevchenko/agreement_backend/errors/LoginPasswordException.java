package me.alexeyshevchenko.agreement_backend.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ${Aleksey} on 06.08.2018.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoginPasswordException extends Exception{
    public LoginPasswordException() {
    }

    public LoginPasswordException(String message) {
        super(message);
    }

    public LoginPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginPasswordException(Throwable cause) {
        super(cause);
    }

    public LoginPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
