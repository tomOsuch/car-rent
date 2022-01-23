package pl.tomaszosuch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.domain.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();
    Optional<Car> findById(Long id);
    Car save(Car car);
    void delete(Car car);
}
