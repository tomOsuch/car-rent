package pl.tomaszosuch.service;

import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.dto.RentDto;

import java.util.List;
import java.util.Optional;

public interface RentService {

    Rent saveRent(final RentDto rentDto);
    List<Rent> getAllRent();
    Optional<Rent> getRentById(final Long id);
    Rent updateRentReturnDate(final RentDto rentDto);
    Rent returnRentedCarById(final Long id);
    void deleteRentById(final Long id);
}
