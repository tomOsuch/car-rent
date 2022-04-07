package pl.tomaszosuch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.dto.RentDto;
import pl.tomaszosuch.exception.RentNotFoundException;
import pl.tomaszosuch.mapper.RentMapper;
import pl.tomaszosuch.service.RentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/rent")
@RequiredArgsConstructor
public class RentController {

    @Autowired
    private final RentServiceImpl rentService;

    @Autowired
    private final RentMapper rentMapper;

    @GetMapping("/getAllRent")
    public List<RentDto> getAllRent() {
        List<Rent> rents = rentService.getAllRent();
        return rentMapper.mapToListRentDto(rents);
    }

    @GetMapping("/getRentById/{id}")
    public RentDto getRentById(@PathVariable Long id) {
        return rentMapper.mapToRentDto(rentService.getRentById(id).orElseThrow(() -> new RentNotFoundException("Rent not found")));
    }

    @DeleteMapping("/deleteRent/{id}")
    public void deleteRent(@PathVariable Long id) {
        rentService.deleteRentById(id);
    }

    @PostMapping(value = "/createRent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody RentDto rentDto) {
        rentService.saveRent(rentDto);
    }

    @PutMapping("/updateRent")
    public RentDto updateRentReturnDate(@RequestBody RentDto rentDto) {
        return rentMapper.mapToRentDto(rentService.updateRentReturnDate(rentDto));
    }

    @PutMapping("/returnCar/{id}")
    public RentDto returnCar(@PathVariable Long id) {
        return rentMapper.mapToRentDto(rentService.returnRentedCarById(id));
    }
}
