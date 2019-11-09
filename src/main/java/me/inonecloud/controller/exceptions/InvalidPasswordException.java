package me.inonecloud.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super();
    }

    public InvalidPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
