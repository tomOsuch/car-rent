package pl.tomaszosuch.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public User(String firstName, String lastName, String cardIdNumber, String drivingLicenseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardIdNumber = cardIdNumber;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}
