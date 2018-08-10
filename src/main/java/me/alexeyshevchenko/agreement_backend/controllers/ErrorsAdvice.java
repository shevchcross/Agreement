package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.dto.ErrorDTO;
import me.alexeyshevchenko.agreement_backend.errors.IdException;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import me.alexeyshevchenko.agreement_backend.errors.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * Created by ${Aleksey} on 06.08.2018.
 */
@RestControllerAdvice
public class ErrorsAdvice {

    @ExceptionHandler(value = {LoginPasswordException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, IdException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleLoginError(Exception e) {
        return new ErrorDTO(400, 400, e.getMessage());
    }
}
