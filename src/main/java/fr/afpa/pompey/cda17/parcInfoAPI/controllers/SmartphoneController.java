package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Smartphone;
import fr.afpa.pompey.cda17.parcInfoAPI.services.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class SmartphoneController {

    @Autowired
    private SmartphoneService smartphoneService;

    @PostMapping("/smartphone")
    public ResponseEntity<Smartphone> createSmartphone(@RequestBody Smartphone smartphone) {
        Smartphone current = smartphoneService.saveSmartphone(smartphone);
        if(current == null) {
            return ResponseEntity.notFound().build();
        }else {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(current.getIdAppareil())
                    .toUri();
            return ResponseEntity.created(location).build();
        }

    }

    @GetMapping("/smartphones")
    public Iterable<Smartphone> getAllSmartphones() {
        return smartphoneService.findAll();
    }

    @GetMapping("/smartphone/{idAppareil}")
    public ResponseEntity<Smartphone> getSmartphoneByIdAppareil(@PathVariable("idAppareil") long idAppareil) {
        Optional<Smartphone> smartphone = smartphoneService.findById(idAppareil);  // Méthode personnalisée dans le repository
        if (smartphone.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(smartphone.get());
    }

    @PutMapping("/smartphone/{id}")
    public ResponseEntity<Smartphone> updateSmartphone(@PathVariable("id") long id, @RequestBody Smartphone smartphone) {
     //Recuperer le smatphone existant grace a l'id
     Optional<Smartphone> s = smartphoneService.findById(id);
     if(s.isPresent()) {
     //si le smartphone existe on recupere l'objet courant
         Smartphone existing = s.get();
     //mettre a jour le champ estSmartphone
     existing.setEstSmartphone(smartphone.isEstSmartphone());
     //mettre a jour le lib de l'appareil
         if(smartphone.getAppareil() != null && smartphone.getAppareil().getLibelle() != null) {
             existing.getAppareil().setLibelle(smartphone.getAppareil().getLibelle());
         }
     //Sauvegarde en base
     Smartphone updated = smartphoneService.saveSmartphone(existing);
     //Retourne 200 ok avec l'objet mis a jour
     return ResponseEntity.ok(updated);
     }
     else{
         //retourne 404 si l'objet n'existe pas
         return ResponseEntity.notFound().build();
     }
    }


    @DeleteMapping("/smartphone/{id}")
    public ResponseEntity<Void> deleteSmartphone(@PathVariable("id") long id) {
        Optional<Smartphone> smartOptional = smartphoneService.findById(id);
        if (smartOptional.isPresent()) {
            smartphoneService.deleteSmartphone(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
