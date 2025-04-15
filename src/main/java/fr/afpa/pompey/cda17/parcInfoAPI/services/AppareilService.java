package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.AppareilRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing Appareil entities.
 * Provides methods for CRUD operations and custom queries.
 */
@Data
@Service
public class AppareilService<T extends Appareil> {

    /**
     * Repository pour l'entité {@link Appareil}.
     * <p>
     * Ce composant est injecté par Spring et permet d'effectuer les opérations CRUD sur les appareils.
     * </p>
     */
    @Autowired
    private AppareilRepository appareilRepository;

    /**
     * Constructor to initialize the AppareilRepository.
     * @param appareilRepository Repository for Appareil entities.
     */
    public AppareilService(AppareilRepository appareilRepository) {
        this.appareilRepository = appareilRepository;
    }

    /**
     * Finds an Appareil by its libelle.
     * @param libelle The libelle of the Appareil.
     * @return An Optional containing the Appareil if found, or empty if not found.
     */
    public Optional<Appareil> findByLibelle(String libelle) {
        // Recherche de l'appareil par libellé via le repository
        return appareilRepository.getAppareilByLibelle(libelle);
    }

    /**
     * Retrieves an Appareil by its ID.
     * @param id The ID of the Appareil.
     * @return An Optional containing the Appareil if found, or empty if not found.
     */
    public Optional<Appareil> getAppareil(long id) {
        // Recherche d'un appareil par son ID dans le repository
        return appareilRepository.findById(id);
    }

    /**
     * Retrieves all Appareil entities.
     * @return An Iterable containing all Appareil entities.
     */
    public Iterable<Appareil> getAppareils() {
        // Retourne tous les appareils trouvés dans la base de données via le repository
        return appareilRepository.findAll();
    }

    /**
     * Deletes an Appareil by its ID.
     * @param id The ID of the Appareil to delete.
     */
    public void deleteAppareil(long id){
        appareilRepository.deleteById(id);
    }

    /**
     * Saves or updates an Appareil entity.
     * @param appareil The Appareil entity to save or update.
     * @return The saved or updated Appareil entity.
     */
    public Appareil save(Appareil appareil) {
        // Enregistre l'appareil dans la base de données via le repository
        return appareilRepository.save(appareil);
    }
}
