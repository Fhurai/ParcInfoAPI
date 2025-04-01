package fr.afpa.pompey.cda17.parcInfoAPI.repositories;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppareilRepository extends CrudRepository<Appareil, Long> {
    Optional<Appareil> getAppareilByLibelle(String libelle);
}
