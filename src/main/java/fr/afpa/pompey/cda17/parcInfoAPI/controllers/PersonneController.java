/**
 * Contrôleur REST pour la gestion des opérations HTTP sur l'entité Personne.
 * <p>
 * Expose des endpoints CRUD standard avec des conventions RESTful.
 * Gère la traduction entre les requêtes HTTP et les opérations métier du service.
 * </p>
 *
 * @author Pompey CDA17
 * @version 1.0
 */
package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * Annotation Spring indiquant que cette classe est un contrôleur REST.
 * Combine @Controller et @ResponseBody pour les réponses automatiques en JSON.
 */
@RestController
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    /**
     * Crée une nouvelle personne via une requête POST.
     *
     * @param personne Entité Personne reçue dans le corps de la requête (format JSON)
     * @return ResponseEntity avec :
     *         - Code 201 Created + Header Location si succès
     *         - Code 204 No Content en cas d'échec
     */
    @PostMapping("/personne")
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        Personne current = personneService.save(personne);

        if(current == null) {
            return ResponseEntity.noContent().build();
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(current.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        }
    }

    /**
     * Récupère toutes les personnes via une requête GET.
     *
     * @return Iterable<Personne> Liste complète des personnes au format JSON
     */
    @GetMapping("/personnes")
    public Iterable<Personne> getAllPersonne()
    {
        return personneService.getPersonnes();
    }

    /**
     * Récupère une personne spécifique par son ID via une requête GET.
     *
     * @param id Identifiant de la personne dans le chemin URL
     * @return Personne trouvée ou null si non existante (code 200 OK dans les deux cas)
     */
    @GetMapping("/personne/{id}")
    public Personne getPersonneById(@PathVariable("id") long id){
        // Fetch the person by ID from the service layer
        Optional<Personne> personne = personneService.getPersonne(id);
        // Return the person if found, otherwise return null
        return personne.orElse(null);
    }

    /**
     * Met à jour une personne existante via une requête PUT.
     *
     * @param personne Nouvelle version de la personne (corps de la requête)
     * @param id Identifiant de la personne à modifier
     * @return Personne mise à jour ou null si non trouvée
     */
    @PutMapping("/personne/{id}")
    public Personne updatePersonne(@RequestBody Personne personne, @PathVariable("id") long id) {
        // Retrieve the existing person by ID
        Optional<Personne> personneOptional = personneService.getPersonne(id);
        if (personneOptional.isPresent()) {
            // If the person exists, get the current person object
            Personne currentPersonne = personneOptional.get();

            // Update the 'nom' field if the new value is not null, not empty, and different
            if(personne.getNom() != null
                    && !personne.getNom().isEmpty()
                    && !personne.getNom().equals(currentPersonne.getNom())){
                currentPersonne.setNom(personne.getNom());
            }

            // Update the 'prenom' field if the new value is not null, not empty, and different
            if(personne.getPrenom() != null
                    && !personne.getPrenom().isEmpty()
                    && !personne.getPrenom().equals(currentPersonne.getPrenom())){
                currentPersonne.setPrenom(personne.getPrenom());
            }

            // Update the 'adresse' field if the new value is not null, not empty, and different
            if(personne.getAdresse() != null
                    && !personne.getAdresse().isEmpty()
                    && !personne.getAdresse().equals(currentPersonne.getAdresse())){
                currentPersonne.setAdresse(personne.getAdresse());
            }

            // Update the 'dateNaissance' field if the new value is not null and different
            if(personne.getDateNaissance() != null
                    && !personne.getDateNaissance()
                    .equals(currentPersonne.getDateNaissance())){
                currentPersonne.setDateNaissance(personne.getDateNaissance());
            }

            // Update the 'telephone' field if the new value is not null and different
            if(personne.getTelephone() != null
                    && !personne.getTelephone()
                    .equals(currentPersonne.getTelephone())){
                currentPersonne.setTelephone(personne.getTelephone());
            }

            // Save the updated person object back to the service layer
            personneService.save(currentPersonne);
            // Return the updated person object
            return currentPersonne;
        } else {
            // If the person does not exist, return null
            return null;
        }
    }

    /**
     * Supprime une personne via une requête DELETE.
     *
     * @param id Identifiant de la personne à supprimer
     */
    @DeleteMapping("/personne/{id}")
    public void deletePersonne(@PathVariable("id") long id) {
        personneService.deletePersonne(id);
    }

    /**
     * Associe un appareil existant à une personne.
     *
     * @param personneId Identifiant de la personne cible
     * @param appareilId Identifiant de l'appareil à associer
     * @return ResponseEntity<Personne> avec la personne mise à jour (code 200)
     */
    @PutMapping("/personne/{personneId}/appareil/{appareilId}")
    public ResponseEntity<Personne> addAppareilToPersonne(
            @PathVariable Long personneId,
            @PathVariable Long appareilId
    ) {
        Personne updatedPersonne = personneService.addAppareilToPersonne(personneId, appareilId);
        return ResponseEntity.ok(updatedPersonne);
    }

    /**
     * Dissocie un appareil d'une personne.
     *
     * @param personneId Identifiant de la personne cible
     * @param appareilId Identifiant de l'appareil à dissocier
     * @return ResponseEntity<Personne> avec la personne mise à jour (code 200)
     */
    @DeleteMapping("/personne/{personneId}/appareil/{appareilId}")
    public ResponseEntity<Personne> removeAppareilToPersonne(
            @PathVariable Long personneId,
            @PathVariable Long appareilId
    ) {
        Personne updatedPersonne =
                personneService.removeAppareilFromPersonne(personneId, appareilId);
        return ResponseEntity.ok(updatedPersonne);
    }

    /**
     * Met à jour la liste complète des appareils associés à une personne.
     *
     * @param appareils Tableau d'identifiants d'appareils
     * @param personneId Identifiant de la personne cible
     * @return ResponseEntity<Personne> avec la personne mise à jour (code 200)
     */
    @PutMapping("/personne/{personneId}/appareils")
    public ResponseEntity<Personne> addAppareilToPersonne(
            @RequestBody String[] appareils,
            @PathVariable Long personneId
    ) {
        Personne updatedPersonne = personneService.updateAppareilsToPersonne(personneId, appareils);
        return ResponseEntity.ok(updatedPersonne);
    }
}