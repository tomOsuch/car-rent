package pl.tomaszosuch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.domain.CarBand;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarBrandRepository extends CrudRepository<CarBand, Long> {

    List<CarBand> findAll();
    Optional<CarBand> findById(Long id);
    CarBand save(CarBand carBand);
    void deleteById(Long id);
}
