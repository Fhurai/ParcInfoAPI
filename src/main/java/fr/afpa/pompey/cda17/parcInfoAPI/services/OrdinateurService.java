package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.OrdinateurRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service

public class OrdinateurService {

    @Autowired
    private OrdinateurRepository ordinateurRepository;

   public List<Ordinateur> getAllOrdinateurs() {
       ordinateurRepository.findAll();
       return List.of();
       //ggggg
   }
}
