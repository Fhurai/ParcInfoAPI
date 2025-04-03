package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PersonneRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PersonneService {

    private PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public Optional<Personne> getPersonne(long id) {
        return personneRepository.findById(id);
    }

    public Iterable<Personne> getPersonnes() {
        return personneRepository.findAll();
    }

    public void deletePersonne(long id) {
        personneRepository.deleteById(id);
    }

    public Personne save(Personne personne) {
        return personneRepository.save(personne);
    }
}
