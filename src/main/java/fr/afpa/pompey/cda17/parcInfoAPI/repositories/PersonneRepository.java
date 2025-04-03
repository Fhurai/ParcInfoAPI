package fr.afpa.pompey.cda17.parcInfoAPI.repositories;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Long> {
}
