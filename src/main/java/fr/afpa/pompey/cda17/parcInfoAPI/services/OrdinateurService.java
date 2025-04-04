package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.OrdinateurRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour la gestion des entités {@link Ordinateur}.
 * <p>
 * Cette classe fournit les méthodes nécessaires pour récupérer, sauvegarder,
 * mettre à jour et supprimer des objets {@link Ordinateur} en interagissant avec
 * le {@link OrdinateurRepository}.
 * </p>
 */
@Data
@Service
public class OrdinateurService {

    /**
     * Constructeur permettant d'injecter le repository pour les opérations CRUD.
     *
     * @param ordinateurRepository le repository associé à l'entité {@link Ordinateur}.
     */
    public OrdinateurService(OrdinateurRepository ordinateurRepository) {
        this.ordinateurRepository = ordinateurRepository;
    }

    /**
     * Repository pour l'entité {@link Ordinateur}.
     * <p>
     * Utilisé pour réaliser les opérations de persistance (création, lecture, mise à jour,
     * suppression) sur les objets {@link Ordinateur}.
     * </p>
     */
    @Autowired
    private OrdinateurRepository ordinateurRepository;

    /**
     * Récupère un {@link Ordinateur} en fonction de son identifiant.
     * <p>
     * Utilise la méthode {@link OrdinateurRepository#findById(Object)} pour rechercher
     * l'ordinateur correspondant à l'ID fourni.
     * </p>
     *
     * @param id l'identifiant de l'ordinateur à récupérer.
     * @return un {@link Optional} contenant l'ordinateur si trouvé, sinon {@link Optional#empty()}.
     */
    public Optional<Ordinateur> getOrdinateurById(long id) {
        // Recherche de l'ordinateur par son ID via le repository
        return ordinateurRepository.findById(id);
    }

    /**
     * Récupère tous les {@link Ordinateur} présents dans la base de données.
     * <p>
     * Utilise la méthode {@link OrdinateurRepository#findAll()} pour obtenir l'ensemble
     * des ordinateurs.
     * </p>
     *
     * @return un {@link Iterable} contenant tous les ordinateurs.
     */
    public Iterable<Ordinateur> getOrdinateurs() {
        // Retourne tous les ordinateurs stockés dans la base via le repository
        return ordinateurRepository.findAll();
    }

    /**
     * Supprime un {@link Ordinateur} en fonction de son identifiant.
     * <p>
     * Utilise la méthode {@link OrdinateurRepository#deleteById(Object)} pour supprimer
     * l'ordinateur correspondant à l'ID fourni.
     * </p>
     *
     * @param id l'identifiant de l'ordinateur à supprimer.
     */
    public void deleteOrdinateur(long id) {
        // Supprime l'ordinateur avec l'ID spécifié via le repository
        ordinateurRepository.deleteById(id);
    }

    /**
     * Sauvegarde ou met à jour un {@link Ordinateur} dans la base de données.
     * <p>
     * Utilise la méthode {@link OrdinateurRepository#save(Object)} pour persister l'ordinateur.
     * </p>
     *
     * @param ordinateur l'ordinateur à sauvegarder ou mettre à jour.
     * @return l'ordinateur sauvegardé avec son identifiant mis à jour.
     */
    public Ordinateur saveOrdinateur(Ordinateur ordinateur) {
        // Enregistre l'ordinateur dans la base de données via le repository
        Ordinateur savedOrdinateur = ordinateurRepository.save(ordinateur);
        // Retourne l'objet sauvegardé
        return savedOrdinateur;
    }
}
