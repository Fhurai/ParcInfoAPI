package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Smartphone;
import fr.afpa.pompey.cda17.parcInfoAPI.services.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SmartphoneController {

    @Autowired
    private SmartphoneService smartphoneService;

    @PostMapping("/smartphones")
    public Smartphone createSmartphone(@RequestBody Smartphone smartphone) {
        return smartphoneService.saveSmartphone(smartphone);
    }

    @GetMapping("/smartphones")
    public Iterable<Smartphone> getAllSmartphones() {
        return smartphoneService.findAll();
    }

    @GetMapping("/smartphones")
    public Smartphone getSmartphoneById(@RequestParam Long id) {
        Optional<Smartphone> smartphone = smartphoneService.findById(id);
        if(smartphone.isPresent()) {
            return smartphone.get();
        }else {
            return null;
        }
    }


}
