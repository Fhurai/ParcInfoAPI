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

    @PostMapping("/peripherique")
    public ResponseEntity<Peripherique> createPeripherique(@RequestBody Peripherique peripherique) {
        Peripherique current = peripheriqueService.save(peripherique);

        if(current == null) {
            return ResponseEntity.noContent().build();
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(current.getIdAppareil())
                    .toUri();

            return ResponseEntity.created(location).build();
        }
    }

    @GetMapping("/peripheriques")
    public Iterable<Peripherique> getPeripheriques(){
        return peripheriqueService.getPeripheriques();
    }

    @GetMapping("/peripherique/{id}")
    public Peripherique getPeripherique(@PathVariable("id") long id){
        Optional<Peripherique> peripherique = peripheriqueService.getPeripherique(id);
        return peripherique.orElse(null);
    }

    @PutMapping("/peripherique/{id}")
    public Peripherique updatePeripherique(@PathVariable("id") long id,
                                           @RequestBody Peripherique peripherique) {
        Optional<Peripherique> peripheriqueOptional = peripheriqueService.getPeripherique(id);
        if (peripheriqueOptional.isPresent()) {
            Peripherique currentPeripherique = peripheriqueOptional.get();

            currentPeripherique.setType(peripherique.getType());

            if(currentPeripherique.getAppareil() != null){
                currentPeripherique.getAppareil()
                        .setLibelle(peripherique.getAppareil()
                                .getLibelle());
            }

            peripheriqueService.save(currentPeripherique);
            return currentPeripherique;
        } else {
            return null;
        }
    }

    @DeleteMapping("/peripherique/{id}")
    public void deletePeripherique(@PathVariable("id") long id){
        peripheriqueService.deletePeripherique(id);
    }

    @PutMapping("/peripherique/{peripheriqueId}/personne/{personneId}")
    public ResponseEntity<Peripherique> addPersonne(@PathVariable(
            "peripheriqueId") long id, @PathVariable("personneId") long personneId) {
        Peripherique updatedPeripherique = peripheriqueService.addPersonne(id, personneId);
        return  ResponseEntity.ok(updatedPeripherique);
    }

    @DeleteMapping("/peripherique/{peripheriqueId}/personne/{personneId}")
    public ResponseEntity<Peripherique> deletePersonne(@PathVariable(
            "peripheriqueId") long id, @PathVariable("personneId") long personneId) {
        Peripherique updatedPeripherique =
                peripheriqueService.removePersonne(id, personneId);
        return  ResponseEntity.ok(null);
    }

    @PutMapping("/peripherique/{peripheriqueId}/personnes")
    public ResponseEntity<Peripherique> updatePersonnes(
            @PathVariable("peripheriqueId") Long peripheriqueId,
            @RequestBody String[] personnes) {
        Peripherique peripherique = peripheriqueService.updatePersonnes(peripheriqueId, personnes);
        return  ResponseEntity.ok(peripherique);
    }
}
