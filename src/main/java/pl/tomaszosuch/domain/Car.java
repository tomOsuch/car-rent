package pl.tomaszosuch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tomaszosuch.enums.State;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CARS")
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    @Column(name = "PRODUCTION_YEAR")
    private LocalDate productionYear;

    @Column(name = "STATE")
    private State state;

    @ManyToOne
    @JoinColumn(name = "CARBRAND_ID")
    private CarBand carBand;

    @OneToOne(cascade = CascadeType.ALL)
    private Rent rent;

    public Car(String registrationNumber, LocalDate productionYear, State state) {
        this.registrationNumber = registrationNumber;
        this.productionYear = productionYear;
        this.state = state;
    }
}
