package pl.tomaszosuch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.tomaszosuch.enums.State;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Long id;
    private Long carBrandId;
    private String registrationNumber;
    private LocalDate productionYear;
    private State state;
}
