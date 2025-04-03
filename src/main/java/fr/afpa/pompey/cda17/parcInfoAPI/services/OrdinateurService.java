package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.OrdinateurRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service

public class OrdinateurService {

    public OrdinateurService(OrdinateurRepository ordinateurRepository) {
        this.ordinateurRepository = ordinateurRepository;
    }

    @Autowired
    private OrdinateurRepository ordinateurRepository;

    public Optional<Ordinateur> getOrdinateurById(long id) {
        return ordinateurRepository.findById(id);
     }

     public Iterable<Ordinateur> getOrdinateurs() {
        return ordinateurRepository.findAll();
     }

     public void deleteOrdinateur(long id) {
        ordinateurRepository.deleteById(id);
     }

     public  Ordinateur saveOrdinateur(Ordinateur ordinateur) {
        Ordinateur savedOrdinateur = ordinateurRepository.save(ordinateur);
        return savedOrdinateur;
     }

}
