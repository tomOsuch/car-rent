package pl.tomaszosuch.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RentValidator {

    public boolean rentValidator(LocalDate rentDate, LocalDate returnDate) {
        return !rentDate.isAfter(returnDate);
    }
}
