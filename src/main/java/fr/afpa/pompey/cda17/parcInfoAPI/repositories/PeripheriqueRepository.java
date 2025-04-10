package fr.afpa.pompey.cda17.parcInfoAPI.repositories;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Peripherique;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for managing {@link Peripherique} entities.
 * <p>
 * This interface extends {@link CrudRepository} to provide CRUD operations
 * for the Peripherique entity. It is automatically implemented by Spring Data JPA.
 * </p>
 */
public interface PeripheriqueRepository extends CrudRepository<Peripherique, Long> {
    // No additional methods are defined here, but custom query methods can be added if needed.
}
