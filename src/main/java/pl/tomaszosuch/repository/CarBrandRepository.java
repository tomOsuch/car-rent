package pl.tomaszosuch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.domain.CarBrand;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarBrandRepository extends CrudRepository<CarBrand, Long> {

    List<CarBrand> findAll();
    Optional<CarBrand> findById(Long id);
    CarBrand save(CarBrand carBand);
    void deleteById(Long id);
}
