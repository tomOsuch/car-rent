package pl.tomaszosuch.validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RentValidatorTest {

    @Autowired
    private RentValidator rentValidator;

    @Test
    public void testRentValidatorDate() {
        //given
        LocalDate rentDate = LocalDate.of(2022,01,01);
        LocalDate returnDate = LocalDate.of(2022, 02, 01);
        //when
        boolean resultValid = rentValidator.rentValidator(rentDate, returnDate);
        //then
        assertTrue(resultValid);
    }
}
