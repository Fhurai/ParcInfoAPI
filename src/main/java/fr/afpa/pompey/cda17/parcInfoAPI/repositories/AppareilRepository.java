package fr.afpa.pompey.cda17.parcInfoAPI.repositories;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository pour l'entité {@link Appareil}.
 * <p>
 * Cette interface étend {@link CrudRepository} afin de fournir les opérations CRUD de base
 * pour l'entité {@link Appareil} dans la base de données.
 * </p>
 * <p>
 * Méthodes personnalisées :
 * <ul>
 *   <li>{@link #getAppareilByLibelle(String)} - Récupère un appareil en fonction de son libellé.</li>
 * </ul>
 * </p>
 */
@Repository
public interface AppareilRepository extends CrudRepository<Appareil, Long> {

    /**
     * Récupère un {@link Appareil} en fonction de son libellé.
     * <p>
     * Cette méthode effectue une recherche dans la base de données en utilisant le champ {@code libelle}.
     * Si un appareil correspondant est trouvé, il est encapsulé dans un {@link Optional}.
     * </p>
     *
     * @param libelle le libellé de l'appareil recherché.
     * @return un {@link Optional} contenant l'appareil si trouvé, sinon un {@link Optional#empty()}.
     */
    Optional<Appareil> getAppareilByLibelle(String libelle);
}
