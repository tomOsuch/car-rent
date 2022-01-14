package pl.tomaszosuch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tomaszosuch.enums.State;

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
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "productionYear")
    private LocalDate productionYear;

    @Column(name = "state")
    private State state;

    public Car(String registrationNumber, LocalDate productionYear, State state) {
        this.registrationNumber = registrationNumber;
        this.productionYear = productionYear;
        this.state = state;
    }
}
