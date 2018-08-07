package me.alexeyshevchenko.agreement_backend.controllers;

import me.alexeyshevchenko.agreement_backend.dto.ErrorDTO;
import me.alexeyshevchenko.agreement_backend.errors.LoginPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by ${Aleksey} on 06.08.2018.
 */
@ControllerAdvice
public class ErrorsAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {LoginPasswordException.class})
    public ResponseEntity<ErrorDTO> handleLoginError(Exception e){
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(400, 400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
