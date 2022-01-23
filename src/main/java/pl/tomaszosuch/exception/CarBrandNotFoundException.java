package pl.tomaszosuch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarBrandNotFoundException extends RuntimeException {

    public CarBrandNotFoundException(String message) {
        super(message);
    }
}
