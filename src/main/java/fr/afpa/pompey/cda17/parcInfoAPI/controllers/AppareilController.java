package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.services.AppareilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AppareilController {

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
        return appareilService.getAppareils();
    }

    /**
     * Recherche un appareil par son identifiant unique.
     *
     * @param id Identifiant de l'appareil (passé dans l'URL).
     * @return L'appareil correspondant à l'ID, ou null si non trouvé.
     *         Retourne un objet Appareil si l'identifiant existe en base de données.
     *         Sinon, retourne null.
     */
    @GetMapping("/appareil/{id}")
    public Appareil getAppareilById(@PathVariable("id") long id) {
        // Recherche l'appareil correspondant à l'identifiant fourni
        Optional<Appareil> appareil = appareilService.getAppareil(id);
        // Retourne l'appareil s'il est trouvé, sinon retourne null
        return appareil.orElse(null);
    }

    /**
     * Met à jour partiellement un appareil existant.
     * Seul le champ 'libelle' peut être modifié dans cette implémentation.
     *
     * @param id        Identifiant de l'appareil à mettre à jour (passé dans l'URL).
     * @param appareil  Objet Appareil contenant les nouvelles valeurs.
     *                  Seul le champ 'libelle' est pris en compte pour la mise à jour.
     * @return L'appareil mis à jour avec les nouvelles valeurs, ou null si l'ID n'existe pas.
     */
    @PutMapping("/appareil/{id}")
    public Appareil updateAppareil(@PathVariable("id") long id,
                                   @RequestBody Appareil appareil) {
        // Recherche l'appareil correspondant à l'identifiant fourni
        Optional<Appareil> appareilOptional = appareilService.getAppareil(id);
        if (appareilOptional.isPresent()) {
            // Si l'appareil existe, récupère l'objet actuel
            Appareil current = appareilOptional.get();

            // Met à jour le champ 'libelle' si une nouvelle valeur est fournie
            String libelle = appareil.getLibelle();
            if(libelle != null) {
                current.setLibelle(libelle);
            }

            // Sauvegarde les modifications en base de données
            appareilService.save(current);
            return current;
        } else {
            // Retourne null si l'appareil n'existe pas
            return null;
        }
    }

    /**
     * Supprime définitivement un appareil par son identifiant.
     *
     * @param id Identifiant de l'appareil à supprimer (passé dans l'URL).
     *           Aucune action n'est effectuée si l'identifiant n'existe pas.
     */
    @DeleteMapping("/appareil/{id}")
    public void deleteAppareil(@PathVariable("id") long id) {
        // Supprime l'appareil correspondant à l'identifiant fourni
        appareilService.deleteAppareil(id);
    }
}