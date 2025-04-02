package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.AppareilRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class AppareilService<T extends Appareil> {

    private AppareilRepository appareilRepository;

    public AppareilService(AppareilRepository appareilRepository) {
        this.appareilRepository = appareilRepository;
    }

    public Optional<Appareil> findByLibelle(String libelle) {
        return appareilRepository.getAppareilByLibelle(libelle);
    }

    public Optional<Appareil> getAppareil(long id) {
        return appareilRepository.findById(id);
    }

    public Iterable<Appareil> getAppareils() {
        return appareilRepository.findAll();
    }

    public void deleteAppareil(long id){
        appareilRepository.deleteById(id);
    }

    public Appareil save(Appareil appareil) {
        return appareilRepository.save(appareil);
    }
}
