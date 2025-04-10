package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Peripherique;
import fr.afpa.pompey.cda17.parcInfoAPI.services.PeripheriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class PeripheriqueController {

    @Autowired
    private PeripheriqueService peripheriqueService;

    /**
     * Creates a new Peripherique.
     * @param peripherique The Peripherique object to be created.
     * @return ResponseEntity with the created Peripherique or no content if creation fails.
     */
    @PostMapping("/peripherique")
    public ResponseEntity<Peripherique> createPeripherique(@RequestBody Peripherique peripherique) {
        // Save the provided Peripherique object to the database
        Peripherique current = peripheriqueService.save(peripherique);

        // Check if the save operation was successful
        if(current == null) {
            // Return a no-content response if the save failed
            return ResponseEntity.noContent().build();
        } else {
            // Build the URI for the newly created resource
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(current.getIdAppareil())
                    .toUri();

            // Return a created response with the resource location
            return ResponseEntity.created(location).build();
        }
    }

    /**
     * Retrieves all Peripheriques.
     * @return An iterable collection of Peripheriques.
     */
    @GetMapping("/peripheriques")
    public Iterable<Peripherique> getPeripheriques(){
        // Retrieve and return all Peripheriques from the service
        return peripheriqueService.getPeripheriques();
    }

    /**
     * Retrieves a specific Peripherique by its ID.
     * @param id The ID of the Peripherique to retrieve.
     * @return The Peripherique object or null if not found.
     */
    @GetMapping("/peripherique/{id}")
    public Peripherique getPeripherique(@PathVariable("id") long id){
        // Retrieve the Peripherique by ID from the service
        Optional<Peripherique> peripherique = peripheriqueService.getPeripherique(id);
        // Return the Peripherique if found, otherwise return null
        return peripherique.orElse(null);
    }

    /**
     * Updates an existing Peripherique.
     * @param id The ID of the Peripherique to update.
     * @param peripherique The updated Peripherique object.
     * @return The updated Peripherique or null if not found.
     */
    @PutMapping("/peripherique/{id}")
    public Peripherique updatePeripherique(@PathVariable("id") long id,
                                           @RequestBody Peripherique peripherique) {
        // Retrieve the existing Peripherique by ID
        Optional<Peripherique> peripheriqueOptional = peripheriqueService.getPeripherique(id);
        if (peripheriqueOptional.isPresent()) {
            // Update the fields of the existing Peripherique
            Peripherique currentPeripherique = peripheriqueOptional.get();
            currentPeripherique.setType(peripherique.getType());

            // Update the associated Appareil's libelle if it exists
            if(currentPeripherique.getAppareil() != null){
                currentPeripherique.getAppareil()
                        .setLibelle(peripherique.getAppareil()
                                .getLibelle());
            }

            // Save the updated Peripherique and return it
            peripheriqueService.save(currentPeripherique);
            return currentPeripherique;
        } else {
            // Return null if the Peripherique was not found
            return null;
        }
    }

    /**
     * Deletes a Peripherique by its ID.
     * @param id The ID of the Peripherique to delete.
     */
    @DeleteMapping("/peripherique/{id}")
    public void deletePeripherique(@PathVariable("id") long id){
        // Delete the Peripherique by ID using the service
        peripheriqueService.deletePeripherique(id);
    }

    /**
     * Associates a Personne with a Peripherique.
     * @param id The ID of the Peripherique.
     * @param personneId The ID of the Personne to associate.
     * @return ResponseEntity with the updated Peripherique.
     */
    @PutMapping("/peripherique/{peripheriqueId}/personne/{personneId}")
    public ResponseEntity<Peripherique> addPersonne(@PathVariable(
            "peripheriqueId") long id, @PathVariable("personneId") long personneId) {
        // Add a Personne to the Peripherique and return the updated Peripherique
        Peripherique updatedPeripherique = peripheriqueService.addPersonne(id, personneId);
        return ResponseEntity.ok(updatedPeripherique);
    }

    /**
     * Removes a Personne association from a Peripherique.
     * @param id The ID of the Peripherique.
     * @param personneId The ID of the Personne to remove.
     * @return ResponseEntity with the updated Peripherique.
     */
    @DeleteMapping("/peripherique/{peripheriqueId}/personne/{personneId}")
    public ResponseEntity<Peripherique> deletePersonne(@PathVariable(
            "peripheriqueId") long id, @PathVariable("personneId") long personneId) {
        // Remove a Personne from the Peripherique and return the updated Peripherique
        Peripherique updatedPeripherique =
                peripheriqueService.removePersonne(id, personneId);
        return ResponseEntity.ok(null);
    }

    /**
     * Updates the list of Personnes associated with a Peripherique.
     * @param peripheriqueId The ID of the Peripherique.
     * @param personnes The array of Personne IDs to associate.
     * @return ResponseEntity with the updated Peripherique.
     */
    @PutMapping("/peripherique/{peripheriqueId}/personnes")
    public ResponseEntity<Peripherique> updatePersonnes(
            @PathVariable("peripheriqueId") Long peripheriqueId,
            @RequestBody String[] personnes) {
        // Update the list of Personnes associated with the Peripherique
        Peripherique peripherique = peripheriqueService.updatePersonnes(peripheriqueId, personnes);
        return ResponseEntity.ok(peripherique);
    }
}
