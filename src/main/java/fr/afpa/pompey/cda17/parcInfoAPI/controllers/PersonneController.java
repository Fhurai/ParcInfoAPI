package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.services.AppareilService;
import fr.afpa.pompey.cda17.parcInfoAPI.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class PersonneController {

    @Autowired
    private PersonneService personneService;

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

    @GetMapping("/personnes")
    public Iterable<Personne> getAllPersonne()
    {
        return personneService.getPersonnes();
    }

    @GetMapping("/personne/{id}")
    public Personne getPersonneById(@PathVariable("id") long id){
        Optional<Personne> personne = personneService.getPersonne(id);
        return personne.orElse(null);
    }

    @PutMapping("/personne/{id}")
    public Personne updatePersonne(@RequestBody Personne personne, @PathVariable("id") long id) {
        Optional<Personne> personneOptional = personneService.getPersonne(id);
        if (personneOptional.isPresent()) {
            Personne currentPersonne = personneOptional.get();

            if(!personne.getNom().isEmpty()
                    && !personne.getNom().equals(currentPersonne.getNom())){
                currentPersonne.setNom(personne.getNom());
            }

            if(!personne.getPrenom().isEmpty()
                    && !personne.getPrenom().equals(currentPersonne.getPrenom())){
                currentPersonne.setPrenom(personne.getPrenom());
            }

            if(!personne.getAdresse().isEmpty()
                    && !personne.getAdresse().equals(currentPersonne.getAdresse())){
                currentPersonne.setAdresse(personne.getAdresse());
            }

            if(personne.getDateNaissance() != null
                    && !personne.getDateNaissance().equals(currentPersonne.getDateNaissance())){
                currentPersonne.setDateNaissance(personne.getDateNaissance());
            }

            if(personne.getTelephone() != null
                    && !personne.getTelephone().equals(currentPersonne.getTelephone())){
                currentPersonne.setTelephone(personne.getTelephone());
            }

            if(personne.getAppareils() != null
                    && !personne.getAppareils().equals(currentPersonne.getAppareils())){
                currentPersonne.setAppareils(personne.getAppareils());
            }

            personneService.save(currentPersonne);
            return currentPersonne;
        } else {
            return null;
        }
    }

    @DeleteMapping("/personne/{id}")
    public void deletePersonne(@PathVariable("id") long id) {
        personneService.deletePersonne(id);
    }
}
