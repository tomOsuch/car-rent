package pl.tomaszosuch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.enums.State;
import pl.tomaszosuch.exception.CarNotFoundException;
import pl.tomaszosuch.exception.RentDateException;
import pl.tomaszosuch.exception.RentNotFoundException;
import pl.tomaszosuch.exception.UserNotFoundException;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.dto.RentDto;
import pl.tomaszosuch.mapper.RentMapper;
import pl.tomaszosuch.repository.CarRepository;
import pl.tomaszosuch.repository.RentRepository;
import pl.tomaszosuch.repository.UserRepository;
import pl.tomaszosuch.validator.RentValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private RentValidator rentValidator;

    private static final String RENT_NOT_FOUND_MESSAGE = "Rent not found";

    @Override
    public Rent saveRent(RentDto rentDto) {
        User user = userRepository.findById(rentDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        Car car = carRepository.findById(rentDto.getCarId()).orElseThrow(() -> new CarNotFoundException("Car not found"));
        if (car.getState().equals("RENTED") || car.getState().equals("DAMAGED")) {
            throw new CarNotFoundException("Car not found");
        }
        if (!rentValidator.rentValidator(rentDto.getRentDate(), rentDto.getReturnDate())) {
            throw new RentDateException("Invalid rent date");
        }
        //ToDo refajktoring metody wydzielenie zapisu do innej metody lub klasy
        Rent rent = rentMapper.mapToRent(rentDto);
        user.getRents().add(rent);
        rent.setUser(user);
        rent.setCar(car);
        car.setState(State.RENTED);
        car.setRent(rent);
        return rentRepository.save(rent);
    }

    @Override
    public List<Rent> getAllRent() {
        return rentRepository.findAll();
    }

    @Override
    public Optional<Rent> getRentById(Long id) {
        return rentRepository.findById(id);
    }

    @Override
    public Rent updateRentReturnDate(RentDto rentDto) {
        Rent rent = rentRepository.findById(rentDto.getId()).orElseThrow(() -> new RentNotFoundException(RENT_NOT_FOUND_MESSAGE));
        if (!rentValidator.rentValidator(rentDto.getRentDate(), rentDto.getReturnDate())){
            throw new RentDateException("Invalid rent date");
        }
        rent.setReturnDate(rentDto.getReturnDate());
        return rentRepository.save(rent);
    }

    @Override
    public Rent returnRentedCarById(Long id) {
        Rent rent = rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException(RENT_NOT_FOUND_MESSAGE));
        rent.setRentDate(LocalDate.now());
        rent.getCar().setState(State.AVAILABLE);
        return rent;
    }

    @Override
    public void deleteRentById(Long id) {
        try {
            rentRepository.deleteById(id);
        } catch (RentNotFoundException e) {
            throw new RentNotFoundException("Rent not found");
        }

    }
}
