package fr.afpa.pompey.cda17.parcInfoAPI.services;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.AppareilRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour la gestion des entités {@link Appareil}.
 * <p>
 * Cette classe encapsule la logique métier relative aux opérations sur les appareils,
 * telles que la recherche par libellé, la récupération, la sauvegarde et la suppression d'appareils.
 * Elle utilise le {@link AppareilRepository} pour interagir avec la base de données.
 * </p>
 *
 * @param <T> un type qui étend {@link Appareil}
 */
@Data
@Service
public class AppareilService<T extends Appareil> {

    /**
     * Repository pour l'entité {@link Appareil}.
     * <p>
     * Ce composant est injecté par Spring et permet d'effectuer les opérations CRUD sur les appareils.
     * </p>
     */
    @Autowired
    private AppareilRepository appareilRepository;

    public AppareilService(AppareilRepository appareilRepository) {
        this.appareilRepository = appareilRepository;
    }

    public Optional<Appareil> findByLibelle(String libelle) {
        // Recherche de l'appareil par libellé via le repository
        return appareilRepository.getAppareilByLibelle(libelle);
    }

    /**
     * Récupère un appareil en fonction de son identifiant.
     * <p>
     * Utilise la méthode {@link AppareilRepository#findById(Object)} pour trouver l'appareil.
     * </p>
     *
     * @param id l'identifiant de l'appareil.
     * @return un {@link Optional} contenant l'appareil si présent, sinon un {@link Optional#empty()}.
     */
    public Optional<Appareil> getAppareil(long id) {
        // Recherche d'un appareil par son ID dans le repository
        return appareilRepository.findById(id);
    }

    /**
     * Récupère la liste de tous les appareils.
     * <p>
     * Utilise la méthode {@link AppareilRepository#findAll()} pour obtenir l'ensemble des appareils.
     * </p>
     *
     * @return un {@link Iterable} contenant tous les appareils.
     */
    public Iterable<Appareil> getAppareils() {
        // Retourne tous les appareils trouvés dans la base de données via le repository
        return appareilRepository.findAll();
    }

    /**
     * Supprime un appareil en fonction de son identifiant.
     * <p>
     * Utilise la méthode {@link AppareilRepository#deleteById(Object)} pour supprimer l'appareil correspondant.
     * </p>
     *
     * @param id l'identifiant de l'appareil à supprimer.
     */
    public void deleteAppareil(long id) {
        // Supprime l'appareil correspondant à l'ID fourni via le repository
        appareilRepository.deleteById(id);
    }

    /**
     * Sauvegarde ou met à jour un appareil.
     * <p>
     * Utilise la méthode {@link AppareilRepository#save(Object)} pour persister l'appareil dans la base de données.
     * </p>
     *
     * @param appareil l'appareil à sauvegarder ou à mettre à jour.
     * @return l'appareil sauvegardé, avec son identifiant éventuellement généré.
     */
    public Appareil save(Appareil appareil) {
        // Enregistre l'appareil dans la base de données via le repository
        return appareilRepository.save(appareil);
    }
}
