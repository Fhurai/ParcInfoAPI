package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.services.AppareilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AppareilController {

    @Autowired
    private AppareilService appareilService;

    @PostMapping("/appareil")
    public Appareil createAppareil(@RequestBody Appareil appareil) {
        return appareilService.save(appareil);
    }

    @GetMapping("/appareils")
    public Iterable<Appareil> getAllAppareil() {
        return appareilService.getAppareils();
    }

    @GetMapping("/appareil/{id}")
    public Appareil getAppareilById(@PathVariable("id") long id) {
        Optional<Appareil> appareil = appareilService.getAppareil(id);
        return appareil.orElse(null);
    }

    @PutMapping("/appareil/{id}")
    public Appareil updateAppareil(@PathVariable("id") long id,
                                   @RequestBody Appareil appareil) {
        Optional<Appareil> appareilOptional = appareilService.getAppareil(id);
        if (appareilOptional.isPresent()) {
            Appareil current = appareilOptional.get();

            String libelle = appareil.getLibelle();
            if(libelle != null) {
                current.setLibelle(libelle);
            }

            appareilService.save(current);
            return current;
        } else {
            return null;
        }
    }

    @DeleteMapping("/appareil/{id}")
    public void deleteAppareil(@PathVariable("id") long id) {
        appareilService.deleteAppareil(id);
    }
}
