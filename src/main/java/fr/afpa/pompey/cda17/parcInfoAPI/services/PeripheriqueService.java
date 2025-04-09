package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Peripherique;
import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PeripheriqueRepository;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PersonneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Data
@Service
public class PeripheriqueService {

    @Autowired
    private PeripheriqueRepository peripheriqueRepository;

    @Autowired
    private PersonneRepository personneRepository;

    public PeripheriqueService(PeripheriqueRepository peripheriqueRepository) {
        this.peripheriqueRepository = peripheriqueRepository;
    }

    public Optional<Peripherique> getPeripherique(long id){
        return peripheriqueRepository.findById(id);
    }

    public Iterable<Peripherique> getPeripheriques() {
        return peripheriqueRepository.findAll();
    }

    public void deletePeripherique(long id){
        peripheriqueRepository.deleteById(id);
    }

    public Peripherique save(Peripherique peripherique){
        return peripheriqueRepository.save(peripherique);
    }

    public Peripherique addPersonne(Long peripheriqueId, Long personneId){
        Peripherique peripherique = peripheriqueRepository.findById(peripheriqueId)
                .orElseThrow(() -> new EntityNotFoundException("Peripherique not found"));
        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new EntityNotFoundException("Personne not found"));

        personne.getAppareils().add(peripherique.getAppareil());
        peripherique.getAppareil().getProprietaires().add(personne);

        return peripheriqueRepository.save(peripherique);
    }

    public Peripherique removePersonne(Long peripheriqueId, Long personneId){
        Peripherique peripherique = peripheriqueRepository.findById(peripheriqueId)
                .orElseThrow(() -> new EntityNotFoundException("Peripherique not found"));
        Personne personne = personneRepository.findById(personneId)
                .orElseThrow(() -> new EntityNotFoundException("Personne not found"));

        personne.getAppareils().remove(peripherique.getAppareil());
        peripherique.getAppareil().getProprietaires().remove(personne);

        return peripheriqueRepository.save(peripherique);
    }

    public Peripherique updatePersonnes(Long peripheriqueId,
                                        String[] personnesId){
        Peripherique peripherique =
                peripheriqueRepository.findById(peripheriqueId)
                        .orElseThrow(() -> new EntityNotFoundException("Peripherique not found"));

        peripherique.getAppareil()
                .getProprietaires()
                .forEach(personne -> {
            personne.getAppareils().remove(peripherique.getAppareil());
        });
        peripherique.getAppareil().getProprietaires().clear();

        Arrays.stream(personnesId).forEach(personneId -> {
            Personne personne =
                    personneRepository.findById(Long.valueOf(personneId))
                            .orElseThrow(() -> new EntityNotFoundException("Personne not found"));
            personne.getAppareils().add(peripherique.getAppareil());
            peripherique.getAppareil().getProprietaires().add(personne);
        });

        return peripheriqueRepository.save(peripherique);
    }
}
