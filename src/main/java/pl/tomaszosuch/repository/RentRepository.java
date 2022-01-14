package pl.tomaszosuch.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.domain.Rent;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository  extends CrudRepository<Rent, Long> {

    List<Rent> findAll();
    Optional<Rent> findById(Long id);
    Rent save(Rent rent);
    void deleteById(Long id);
}
