package pl.tomaszosuch.mapper;

import org.springframework.stereotype.Component;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.dto.RentDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentMapper {

    public Rent mapToRent(final RentDto rentDto) {
        return new Rent(
            rentDto.getRentDate(),
            rentDto.getReturnDate()
        );
    }

    public RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                rent.getUser().getId(),
                rent.getCar().getId(),
                rent.getRentDate(),
                rent.getReturnDate()
        );
    }

    public List<Rent> mapToRentList(final List<RentDto> rentDtoList) {
        return rentDtoList.stream()
                .map(this::mapToRent)
                .collect(Collectors.toList());
    }

    public List<RentDto> mapToListRentDto(final List<Rent> rents) {
        return rents.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
