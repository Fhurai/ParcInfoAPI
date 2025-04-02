package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
import fr.afpa.pompey.cda17.parcInfoAPI.services.OrdinateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class OrdinateurController {

    @Autowired
    private OrdinateurService ordinateurService;

    @PostMapping("/ordinateur")
    public Ordinateur ordinateur(@RequestBody Ordinateur ordinateur) {
        return OrdinateurService.saveOrdinateur(ordinateur);
    }

    @GetMapping ("/ordinateurs")
    public Iterable<Ordinateur> getOrdinateurs() {
        return ordinateurService.getOrdinateurs();
    }

    @GetMapping("/ordinateur/{id}")
    public Ordinateur getOrdinateur(@PathVariable int id) {
        Optional<Ordinateur> ordinateur = ordinateurService.getOrdinateurById(id);
        if (ordinateur.isPresent()) {
            return ordinateur.get();
        } else {
            return null;
        }
    }

    @PutMapping("/ordinateur/{id}")
    public Ordinateur updateOrdinateur(@PathVariable int id, @RequestBody Ordinateur ordinateur) {
        Optional<Ordinateur> e = ordinateurService.getOrdinateurById(id);
        if (e.isPresent()) {
            Ordinateur current = e.get();

            current.setDeBureau(ordinateur.getDeBureau());

            return OrdinateurService.saveOrdinateur(current);
        } else {
            return null;

        }
    }

    @DeleteMapping("/ordinateur/{id}")
    public void deleteOrdinateur(@PathVariable int id) {
        ordinateurService.deleteOrdinateur(id);
    }


}

