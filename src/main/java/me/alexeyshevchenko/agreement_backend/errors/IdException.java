package me.alexeyshevchenko.agreement_backend.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ${Aleksey} on 08.08.2018.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdException extends Exception {
    public IdException(String message) {
        super(message);
    }
}
