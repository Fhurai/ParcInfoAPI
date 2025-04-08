package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Smartphone;
import fr.afpa.pompey.cda17.parcInfoAPI.services.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/smartphone/{id}")
    public Smartphone getSmartphoneById(@PathVariable("id") long id) {
        Optional<Smartphone> smartphone = smartphoneService.findById(id);
        if(smartphone.isPresent()) {
            return smartphone.get();
        }else {
            return null;
        }
    }
    /*@PutMapping("/smattphone/{id}")
    public ResponseEntity<Smartphone> updateSmartphone(@PathVariable("id") long id, @RequestBody Smartphone smartphone) {
        try{
     //Recuperer le smatphone existant grace a l'id
     Optional<Smartphone> s = smartphoneService.findById(id);
     if(s.isPresent()) {
     //si le smartphone existe on recupere l'objet courant
         Smartphone current = s.get();
     //mettre a jour le champ estSmartphone
     current.setEstSmartphone(smartphone.getEstSmartphone());
     }
    }

     */


}
