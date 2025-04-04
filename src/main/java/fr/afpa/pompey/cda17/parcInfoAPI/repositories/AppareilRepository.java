/**
 * Interface de repository pour l'entité Appareil.
 * <p>
 * Cette interface fournit des méthodes CRUD standard pour l'entité Appareil grâce à l'héritage
 * de CrudRepository de Spring Data. Elle permet d'interagir avec la base de données sans implémentation explicite.
 * </p>
 *
 * @author Pompey CDA17
 * @version 1.0
 */
package fr.afpa.pompey.cda17.parcInfoAPI.repositories;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Annotation Spring indiquant que cette interface est un composant de persistence.
 * Elle active la traduction automatique des exceptions de persistence.
 */
@Repository
public interface AppareilRepository extends CrudRepository<Appareil, Long> {
    /**
     * Hérite automatiquement des opérations CRUD standards :
     * <ul>
     *   <li>save(entity) : Sauvegarde une entité</li>
     *   <li>findById(id) : Recherche par identifiant</li>
     *   <li>existsById(id) : Vérifie l'existence</li>
     *   <li>findAll() : Liste toutes les entités</li>
     *   <li>deleteById(id) : Supprime par identifiant</li>
     *   <li>count() : Compte le nombre d'entités</li>
     * </ul>
     *
     * Les méthodes peuvent être étendues en ajoutant des signatures de méthode personnalisées
     * suivant la nomenclature de Spring Data (ex: findByNom(String nom))
     */

    /**
     * Recherche par libelle
     * @param libelle Libellé de l'appareil
     * @return Appareil existant ou non.
     */
    Optional<Appareil> getAppareilByLibelle(String libelle);
}
