package pl.tomaszosuch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarBand {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "construction_year")
    private LocalDate constructionYear;

    public CarBand(String brandName, LocalDate constructionYear) {
        this.brandName = brandName;
        this.constructionYear = constructionYear;
    }
}
