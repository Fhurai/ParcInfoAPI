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
     * Crée un nouvel appareil en base de données.
     *
     * @param appareil Objet Appareil reçu au format JSON dans le corps de la requête.
     *                 Doit contenir les champs nécessaires à la création.
     * @return L'appareil créé avec son identifiant généré.
     */
    @PostMapping("/appareil")
    public Appareil createAppareil(@RequestBody Appareil appareil) {
        // Sauvegarde l'appareil en base de données via le service
        return appareilService.save(appareil);
    }

    /**
     * Récupère la liste de tous les appareils existants.
     *
     * @return Un Iterable contenant tous les appareils en base de données.
     *         Retourne une liste vide si aucun résultat.
     */
    @GetMapping("/appareils")
    public Iterable<Appareil> getAllAppareil() {
        // Appel au service pour obtenir la liste de tous les appareils
        return appareilService.getAppareils();
    }

    /**
     * Recherche un appareil par son identifiant unique.
     *
     * @param id Identifiant de l'appareil (passé dans l'URL).
     * @return L'appareil correspondant à l'ID, ou null si non trouvé.
     */
    @GetMapping("/appareil/{id}")
    public Appareil getAppareilById(@PathVariable("id") long id) {
        // Recherche de l'appareil par son ID via le service
        Optional<Appareil> appareil = appareilService.getAppareil(id);
        // Retourne l'appareil si trouvé, sinon retourne null
        return appareil.orElse(null);
    }

    /**
     * Met à jour partiellement un appareil existant.
     * Seul le libellé peut être modifié dans cette implémentation.
     *
     * @param id        Identifiant de l'appareil à mettre à jour.
     * @param appareil  Objet Appareil contenant les nouvelles valeurs.
     *                  Seul le champ 'libelle' est pris en compte.
     * @return L'appareil mis à jour, ou null si l'ID n'existe pas.
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
     * Supprime définitivement un appareil par son identifiant.
     *
     * @param id Identifiant de l'appareil à supprimer.
     *           Aucune action si l'ID n'existe pas.
     */
    @DeleteMapping("/appareil/{id}")
    public void deleteAppareil(@PathVariable("id") long id) {
        // Appel au service pour supprimer l'appareil par son ID
        appareilService.deleteAppareil(id);
    }
}