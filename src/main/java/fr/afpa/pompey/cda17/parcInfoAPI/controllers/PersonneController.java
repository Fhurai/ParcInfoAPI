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
import fr.afpa.pompey.cda17.parcInfoAPI.services.AppareilService;
import fr.afpa.pompey.cda17.parcInfoAPI.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

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
        Optional<Personne> personne = personneService.getPersonne(id);
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
        Optional<Personne> personneOptional = personneService.getPersonne(id);
        if (personneOptional.isPresent()) {
            Personne currentPersonne = personneOptional.get();

            // Mise à jour conditionnelle de chaque champ
            if(!personne.getNom().isEmpty() && !personne.getNom().equals(currentPersonne.getNom())){
                currentPersonne.setNom(personne.getNom());
            }

            if(!personne.getPrenom().isEmpty() && !personne.getPrenom().equals(currentPersonne.getPrenom())){
                currentPersonne.setPrenom(personne.getPrenom());
            }

            if(!personne.getAdresse().isEmpty() && !personne.getAdresse().equals(currentPersonne.getAdresse())){
                currentPersonne.setAdresse(personne.getAdresse());
            }

            if(personne.getDateNaissance() != null && !personne.getDateNaissance().equals(currentPersonne.getDateNaissance())){
                currentPersonne.setDateNaissance(personne.getDateNaissance());
            }

            if(personne.getTelephone() != null && !personne.getTelephone().equals(currentPersonne.getTelephone())){
                currentPersonne.setTelephone(personne.getTelephone());
            }

            personneService.save(currentPersonne);
            return currentPersonne;
        } else {
            return null;
        }
    }

    /**
     * Supprime une personne via une requête DELETE.
     *
     * @param id Identifiant de la personne à supprimer
     * @return Void (code 200 OK même si l'entité n'existe pas)
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
    @PutMapping("/personnes/{personneId}/appareil/{appareilId}")
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
    @DeleteMapping("/personnes/{personneId}/appareil/{appareilId}")
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
    @PutMapping("/personnes/{personneId}/appareils")
    public ResponseEntity<Personne> addAppareilToPersonne(
            @RequestBody String[] appareils,
            @PathVariable Long personneId
    ) {
        Personne updatedPersonne = personneService.updateAppareilsToPersonne(personneId, appareils);
        return ResponseEntity.ok(updatedPersonne);
    }
}