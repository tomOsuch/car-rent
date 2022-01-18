package pl.tomaszosuch.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "card_id_number")
    private String cardIdNumber;

    @Column(name = "driving_license_number")
    private String drivingLicenseNumber;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> rents;

    public User(String firstName, String lastName, String cardIdNumber, String drivingLicenseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardIdNumber = cardIdNumber;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}
