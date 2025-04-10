package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.AppareilRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing Appareil entities.
 * Provides methods for CRUD operations and custom queries.
 */
@Data
@Service
public class AppareilService<T extends Appareil> {

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
        return appareilRepository.getAppareilByLibelle(libelle);
    }

    /**
     * Retrieves an Appareil by its ID.
     * @param id The ID of the Appareil.
     * @return An Optional containing the Appareil if found, or empty if not found.
     */
    public Optional<Appareil> getAppareil(long id) {
        return appareilRepository.findById(id);
    }

    /**
     * Retrieves all Appareil entities.
     * @return An Iterable containing all Appareil entities.
     */
    public Iterable<Appareil> getAppareils() {
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
        return appareilRepository.save(appareil);
    }
}
