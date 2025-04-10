package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Peripherique;
import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PeripheriqueRepository;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PersonneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Data
@Service
public class PeripheriqueService {

    @Autowired
    private PeripheriqueRepository peripheriqueRepository;

    @Autowired
    private PersonneRepository personneRepository;

    public PeripheriqueService(PeripheriqueRepository peripheriqueRepository) {
        this.peripheriqueRepository = peripheriqueRepository;
    }

    /**
     * Retrieves a Peripherique by its ID.
     * @param id The ID of the Peripherique.
     * @return An Optional containing the Peripherique if found, or empty otherwise.
     */
    public Optional<Peripherique> getPeripherique(long id){
        // Fetch the Peripherique by its ID from the repository
        return peripheriqueRepository.findById(id);
    }

    /**
     * Retrieves all Peripheriques.
     * @return An Iterable containing all Peripheriques.
     */
    public Iterable<Peripherique> getPeripheriques() {
        // Retrieve all Peripheriques from the repository
        return peripheriqueRepository.findAll();
    }

    /**
     * Deletes a Peripherique by its ID.
     * @param id The ID of the Peripherique to delete.
     */
    public void deletePeripherique(long id){
        // Delete the Peripherique with the given ID from the repository
        peripheriqueRepository.deleteById(id);
    }

    /**
     * Saves a Peripherique to the repository.
     * @param peripherique The Peripherique to save.
     * @return The saved Peripherique.
     */
    public Peripherique save(Peripherique peripherique){
        // Save the given Peripherique to the repository and return the saved entity
        return peripheriqueRepository.save(peripherique);
    }

    /**
     * Associates a Personne with a Peripherique.
     * @param peripheriqueId The ID of the Peripherique.
     * @param personneId The ID of the Personne.
     * @return The updated Peripherique.
     */
    public Peripherique addPersonne(Long peripheriqueId, Long personneId){
        // Fetch the Peripherique by its ID, throw an exception if not found
        Peripherique peripherique = peripheriqueRepository.findById(peripheriqueId)
                .orElseThrow(() -> new EntityNotFoundException("Peripherique not found"));
        // Fetch the Personne by its ID, throw an exception if not found
        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new EntityNotFoundException("Personne not found"));

        // Add the Peripherique's Appareil to the Personne's list of Appareils
        personne.getAppareils().add(peripherique.getAppareil());
        // Add the Personne to the Peripherique's Appareil's list of Proprietaires
        peripherique.getAppareil().getProprietaires().add(personne);

        // Save the updated Peripherique to the repository and return it
        return peripheriqueRepository.save(peripherique);
    }

    /**
     * Removes the association of a Personne from a Peripherique.
     * @param peripheriqueId The ID of the Peripherique.
     * @param personneId The ID of the Personne.
     * @return The updated Peripherique.
     */
    public Peripherique removePersonne(Long peripheriqueId, Long personneId){
        // Fetch the Peripherique by its ID, throw an exception if not found
        Peripherique peripherique = peripheriqueRepository.findById(peripheriqueId)
                .orElseThrow(() -> new EntityNotFoundException("Peripherique not found"));
        // Fetch the Personne by its ID, throw an exception if not found
        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new EntityNotFoundException("Personne not found"));

        // Remove the Peripherique's Appareil from the Personne's list of Appareils
        personne.getAppareils().remove(peripherique.getAppareil());
        // Remove the Personne from the Peripherique's Appareil's list of Proprietaires
        peripherique.getAppareil().getProprietaires().remove(personne);

        // Save the updated Peripherique to the repository and return it
        return peripheriqueRepository.save(peripherique);
    }

    /**
     * Updates the list of Personnes associated with a Peripherique.
     * @param peripheriqueId The ID of the Peripherique.
     * @param personnesId An array of Personne IDs to associate with the Peripherique.
     * @return The updated Peripherique.
     */
    public Peripherique updatePersonnes(Long peripheriqueId,
                                        String[] personnesId){
        // Fetch the Peripherique by its ID, throw an exception if not found
        Peripherique peripherique =
                peripheriqueRepository.findById(peripheriqueId)
                        .orElseThrow(() -> new EntityNotFoundException("Peripherique not found"));

        // Remove all existing associations between the Peripherique's Appareil and its Proprietaires
        peripherique.getAppareil()
                .getProprietaires()
                .forEach(personne -> {
            personne.getAppareils().remove(peripherique.getAppareil());
        });
        peripherique.getAppareil().getProprietaires().clear();

        // For each Personne ID in the input array, fetch the Personne and associate it with the Peripherique's Appareil
        Arrays.stream(personnesId).forEach(personneId -> {
            Personne personne =
                    personneRepository.findById(Long.valueOf(personneId))
                            .orElseThrow(() -> new EntityNotFoundException("Personne not found"));
            personne.getAppareils().add(peripherique.getAppareil());
            peripherique.getAppareil().getProprietaires().add(personne);
        });

        // Save the updated Peripherique to the repository and return it
        return peripheriqueRepository.save(peripherique);
    }
}
