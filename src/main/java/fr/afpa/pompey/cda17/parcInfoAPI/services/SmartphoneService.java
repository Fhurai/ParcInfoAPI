package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Smartphone;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.SmartphoneRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@Service
public class SmartphoneService {
    public SmartphoneService(SmartphoneRepository smartphoneRepository) {
        this.smartphoneRepository = smartphoneRepository;
    }

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    public Optional<Smartphone> findById(Long id) {
        return smartphoneRepository.findById(id);
    }

    public Iterable<Smartphone> findAll() {
        return smartphoneRepository.findAll();
    }
    public Smartphone saveSmartphone(Smartphone smartphone) {
        return smartphoneRepository.save(smartphone);
    }

    public Smartphone update(Smartphone smartphone) {
        return smartphoneRepository.save(smartphone);
    }

    public void deleteSmartphone(Long id) {
        smartphoneRepository.deleteById(id);
    }
}
