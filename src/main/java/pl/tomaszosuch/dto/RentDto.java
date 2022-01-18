package pl.tomaszosuch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

    private Long id;
    private Long userId;
    private Long carId;
    private LocalDate rentDate;
    private LocalDate returnDate;
}
