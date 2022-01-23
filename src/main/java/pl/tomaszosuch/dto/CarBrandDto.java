package pl.tomaszosuch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandDto {

    private Long id;
    private String brandName;
    private LocalDate constructionYear;
}
