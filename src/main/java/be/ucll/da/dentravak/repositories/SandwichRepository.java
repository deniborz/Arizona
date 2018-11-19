package be.ucll.da.dentravak.repositories;

import be.ucll.da.dentravak.model.Sandwich;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SandwichRepository extends CrudRepository<Sandwich, UUID> {
    boolean existsById(UUID id);
    Optional<Sandwich> findById(UUID id);
    List<Sandwich> findAll();
}

