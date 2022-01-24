package pl.tomaszosuch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    public Rent(LocalDate rentDate, LocalDate returnDate) {
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public Rent(Long id, LocalDate rentDate, LocalDate returnDate) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
}
