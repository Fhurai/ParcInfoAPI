package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.services.AppareilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des appareils dans l'API ParcInfo.
 * <p>
 * Ce contrôleur expose des endpoints pour créer, récupérer, mettre à jour et supprimer
 * des objets {@link Appareil}. Il utilise le service {@link AppareilService} pour accéder
 * à la logique métier et aux opérations de persistance.
 * </p>
 */
@RestController
public class AppareilController {

    /**
     * Service chargé de gérer les opérations sur les entités {@link Appareil}.
     * Il est injecté automatiquement par Spring grâce à l'annotation {@code @Autowired}.
     */
    @Autowired
    private AppareilService appareilService;

    /**
     * Crée un nouvel appareil.
     * <p>
     * Endpoint HTTP POST qui permet de sauvegarder un nouvel objet {@link Appareil}.
     * Le corps de la requête doit contenir les informations de l'appareil.
     * </p>
     *
     * @param appareil l'objet {@link Appareil} à créer.
     * @return l'objet {@link Appareil} créé après sauvegarde.
     */
    @PostMapping("/appareil")
    public Appareil createAppareil(@RequestBody Appareil appareil) {
        // Sauvegarde l'appareil en base de données via le service
        return appareilService.save(appareil);
    }

    /**
     * Récupère la liste de tous les appareils enregistrés.
     * <p>
     * Endpoint HTTP GET qui renvoie un {@link Iterable} de tous les {@link Appareil} existants.
     * </p>
     *
     * @return un {@link Iterable} contenant l'ensemble des {@link Appareil}.
     */
    @GetMapping("/appareils")
    public Iterable<Appareil> getAllAppareil() {
        // Appel au service pour obtenir la liste de tous les appareils
        return appareilService.getAppareils();
    }

    /**
     * Récupère un appareil en fonction de son identifiant.
     * <p>
     * Endpoint HTTP GET qui permet de récupérer un {@link Appareil} selon son ID.
     * Si l'appareil n'est pas trouvé, {@code null} est retourné.
     * </p>
     *
     * @param id l'identifiant de l'appareil à récupérer.
     * @return l'objet {@link Appareil} correspondant ou {@code null} si non trouvé.
     */
    @GetMapping("/appareil/{id}")
    public Appareil getAppareilById(@PathVariable("id") long id) {
        // Recherche de l'appareil par son ID via le service
        Optional<Appareil> appareil = appareilService.getAppareil(id);
        // Retourne l'appareil si trouvé, sinon retourne null
        return appareil.orElse(null);
    }

    /**
     * Met à jour les informations d'un appareil existant.
     * <p>
     * Endpoint HTTP PUT qui permet de modifier le {@code libelle} d'un {@link Appareil}.
     * Si l'appareil n'est pas trouvé, {@code null} est retourné.
     * </p>
     *
     * @param id       l'identifiant de l'appareil à mettre à jour.
     * @param appareil l'objet {@link Appareil} contenant les nouvelles informations.
     * @return l'objet {@link Appareil} mis à jour ou {@code null} si l'appareil n'existe pas.
     */
    @PutMapping("/appareil/{id}")
    public Appareil updateAppareil(@PathVariable("id") long id,
                                   @RequestBody Appareil appareil) {
        // Recherche de l'appareil existant via son ID
        Optional<Appareil> appareilOptional = appareilService.getAppareil(id);
        if (appareilOptional.isPresent()) {
            // Récupère l'objet courant
            Appareil current = appareilOptional.get();

            // Récupère la nouvelle valeur du libellé
            String libelle = appareil.getLibelle();
            if (libelle != null) {
                // Met à jour le libellé de l'appareil
                current.setLibelle(libelle);
            }

            // Sauvegarde les modifications apportées à l'appareil
            appareilService.save(current);
            // Retourne l'appareil mis à jour
            return current;
        } else {
            // Retourne null si l'appareil n'existe pas
            return null;
        }
    }

    /**
     * Supprime un appareil en fonction de son identifiant.
     * <p>
     * Endpoint HTTP DELETE qui supprime le {@link Appareil} correspondant à l'ID fourni.
     * </p>
     *
     * @param id l'identifiant de l'appareil à supprimer.
     */
    @DeleteMapping("/appareil/{id}")
    public void deleteAppareil(@PathVariable("id") long id) {
        // Appel au service pour supprimer l'appareil par son ID
        appareilService.deleteAppareil(id);
    }
}
