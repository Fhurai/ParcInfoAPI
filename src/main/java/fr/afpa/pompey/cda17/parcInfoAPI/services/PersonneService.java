/**
 * Service de gestion des opérations métier pour l'entité Personne.
 * <p>
 * Ce service fait office de couche intermédiaire entre les contrôleurs et le repository,
 * implémentant la logique métier liée à la gestion des personnes.
 * </p>
 *
 * @author Pompey CDA17
 * @version 1.0
 */
package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PersonneRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Annotation Lombok générant automatiquement les getters/setters et méthodes utilitaires.
 * Annotation Spring spécifiant que cette classe est un composant de service.
 */
@Data
@Service
@Transactional
public class PersonneService {

    /**
     * Repository injecté pour les opérations de persistance des personnes.
     */
    private PersonneRepository personneRepository;

    /**
     * Constructeur pour l'injection de dépendance du repository.
     *
     * @param personneRepository Le repository des personnes à injecter
     */
    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    /**
     * Récupère une personne par son identifiant unique.
     *
     * @param id Identifiant de la personne recherchée
     * @return Un Optional contenant la personne trouvée ou vide
     */
    public Optional<Personne> getPersonne(long id) {
        return personneRepository.findById(id);
    }

    /**
     * Récupère la liste complète de toutes les personnes enregistrées.
     *
     * @return Un Iterable contenant toutes les entités Personne
     */
    public Iterable<Personne> getPersonnes() {
        return personneRepository.findAll();
    }

    /**
     * Supprime une personne de la base de données selon son identifiant.
     *
     * @param id Identifiant de la personne à supprimer
     * @throws org.springframework.dao.EmptyResultDataAccessException Si l'ID n'existe pas
     */
    public void deletePersonne(long id) {
        personneRepository.deleteById(id);
    }

    /**
     * Sauvegarde ou met à jour une personne dans la base de données.
     *
     * @param personne L'entité Personne à persister
     * @return L'entité Personne sauvegardée avec son ID généré
     */
    public Personne save(Personne personne) {
        return personneRepository.save(personne);
    }
}