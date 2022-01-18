package pl.tomaszosuch.service;

import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.dto.RentDto;

import java.util.List;

public interface RentService {

    Rent saveRent(final RentDto rentDto);
    List<RentDto> getAllRent();
    RentDto getRentById(final Long id);
    RentDto updateRentReturnDate(final RentDto rentDto);
    RentDto returnRentedCarById(final Long id);
    void deleteRentById(final Long id);
}
