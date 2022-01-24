package pl.tomaszosuch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarBrand {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "construction_year")
    private LocalDate constructionYear;

    @OneToMany(
            targetEntity = Car.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Car> cars;

    public CarBrand(String brandName, LocalDate constructionYear) {
        this.brandName = brandName;
        this.constructionYear = constructionYear;
    }

    public CarBrand(String brandName, LocalDate constructionYear, List<Car> cars) {
        this.brandName = brandName;
        this.constructionYear = constructionYear;
        this.cars = cars;
    }

    public CarBrand(Long id, String brandName, LocalDate constructionYear) {
        this.id = id;
        this.brandName = brandName;
        this.constructionYear = constructionYear;
    }
}
