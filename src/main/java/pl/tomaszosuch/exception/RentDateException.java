package pl.tomaszosuch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RentDateException extends RuntimeException {

    public RentDateException(String message) {
        super(message);
    }
}
