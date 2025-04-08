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
<<<<<<< HEAD
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
=======
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
>>>>>>> develop
     */
    Optional<Appareil> getAppareilByLibelle(String libelle);
}
