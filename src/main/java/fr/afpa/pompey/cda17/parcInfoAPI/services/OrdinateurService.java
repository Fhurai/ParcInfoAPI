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

    @Autowired
    private static OrdinateurRepository ordinateurRepository;

    public Optional<Ordinateur> getOrdinateurById(int id) {
        return ordinateurRepository.findById(id);
     }

     public Iterable<Ordinateur> getOrdinateurs() {
        return ordinateurRepository.findAll();
     }

     public void deleteOrdinateur(int id) {
        ordinateurRepository.deleteById(id);
     }

     public static Ordinateur saveOrdinateur(Ordinateur ordinateur) {
        Ordinateur savedOrdinateur = ordinateurRepository.save(ordinateur);
        return savedOrdinateur;
     }

}
