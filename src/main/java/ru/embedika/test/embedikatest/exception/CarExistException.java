package ru.embedika.test.embedikatest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CarExistException extends RuntimeException {
    public CarExistException(String message) {
        super(message);
    }
}
