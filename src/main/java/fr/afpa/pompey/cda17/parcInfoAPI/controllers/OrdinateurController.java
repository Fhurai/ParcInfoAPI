package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
import fr.afpa.pompey.cda17.parcInfoAPI.services.OrdinateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des ordinateurs dans l'API ParcInfo.
 * <p>
 * Ce contrôleur fournit des endpoints permettant de créer, récupérer, mettre à jour
 * et supprimer des objets {@link Ordinateur}. Chaque méthode correspond à une
 * opération HTTP et interagit avec le service {@link OrdinateurService} qui encapsule
 * la logique métier.
 * </p>
 */
@RestController
public class OrdinateurController {

    /**
     * Service chargé de gérer les opérations sur les objets {@link Ordinateur}.
     * Ce service est injecté automatiquement par Spring grâce à l'annotation {@code @Autowired}.
     */
    @Autowired
    private OrdinateurService ordinateurService;

    /**
     * Crée un nouvel ordinateur.
     * <p>
     * L'objet {@link Ordinateur} est envoyé dans le corps de la requête HTTP POST.
     * Ce endpoint permet d'enregistrer un nouvel ordinateur en base de données.
     * </p>
     *
     * @param ordinateur L'objet {@link Ordinateur} à créer, extrait du corps de la requête.
     * @return L'objet {@link Ordinateur} créé après son enregistrement.
     */
    @PostMapping("/ordinateur")
    public ResponseEntity<Ordinateur> createOrdinateur(@RequestBody Ordinateur ordinateur) {
        // Vérifie que l'objet ne contient pas d'identifiant (ou qu'il est à sa valeur par défaut, ici 0)
        if (ordinateur.getAppareil().getId() != 0) {
            // Le client ne devrait pas définir l'id, on retourne une mauvaise requête
            return ResponseEntity.badRequest().build();
        }
        // Sauvegarde l'objet Ordinateur
        Ordinateur savedOrdinateur = ordinateurService.saveOrdinateur(ordinateur);
        // Retourne l'objet sauvegardé avec un statut HTTP 200 (OK)
        return ResponseEntity.ok(savedOrdinateur);
    }


    /**
     * Récupère la liste de tous les ordinateurs enregistrés.
     * <p>
     * Ce endpoint est accessible via une requête HTTP GET et retourne
     * une collection itérable de tous les objets {@link Ordinateur}.
     * </p>
     *
     * @return Un {@link Iterable} contenant tous les objets {@link Ordinateur}.
     */
    @GetMapping("/ordinateurs")
    public Iterable<Ordinateur> getOrdinateurs() {
        // Récupère la liste de tous les ordinateurs via le service
        return ordinateurService.getOrdinateurs();
    }

    /**
     * Récupère un ordinateur en fonction de son identifiant.
     * <p>
     * Ce endpoint accepte une requête HTTP GET avec un identifiant en paramètre d'URL.
     * Si l'ordinateur est trouvé, il est retourné avec un statut HTTP 200 (OK),
     * sinon une réponse HTTP 404 (Not Found) est renvoyée.
     * </p>
     *
     * @param id L'identifiant de l'ordinateur à récupérer.
     * @return Une {@link ResponseEntity} contenant l'objet {@link Ordinateur} ou une réponse 404.
     */
    @GetMapping("/ordinateur/{id}")
    public ResponseEntity<Ordinateur> getOrdinateur(@PathVariable  long id) {
        try {
            // Tente de récupérer l'ordinateur correspondant à l'id fourni
            Optional<Ordinateur> ordinateur = ordinateurService.getOrdinateurById(id);

            // Si l'ordinateur est présent, renvoie l'objet avec un code HTTP 200 (OK)
            // Sinon, renvoie une réponse HTTP 404 (Not Found)
            return ordinateur.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            // En cas d'exception, vous pouvez enregistrer l'erreur dans vos logs
            // Par exemple, si vous avez un logger configuré :
            // logger.error("Erreur lors de la récupération de l'ordinateur avec l'id " + id, e);

            // Retourne une réponse HTTP 500 (Internal Server Error) pour signaler une erreur côté serveur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Met à jour les informations d'un ordinateur existant.
     * <p>
     * Ce endpoint accepte une requête HTTP PUT avec l'identifiant de l'ordinateur
     * à mettre à jour et les nouvelles données dans le corps de la requête.
     * Ici, seul le champ {@code deBureau} est mis à jour à titre d'exemple.
     * </p>
     *
     * @param id         L'identifiant de l'ordinateur à mettre à jour.
     * @param ordinateur L'objet {@link Ordinateur} contenant les nouvelles informations.
     * @return L'objet {@link Ordinateur} mis à jour ou {@code null} si l'ordinateur n'existe pas.
     */
    @PutMapping("/ordinateur/{id}")
    public Ordinateur updateOrdinateur(@PathVariable long id, @RequestBody Ordinateur ordinateur) {
        // Récupère l'ordinateur existant grâce à l'id
        Optional<Ordinateur> e = ordinateurService.getOrdinateurById(id);
        if (e.isPresent()) {
            // Si l'ordinateur existe, récupère l'objet courant
            Ordinateur current = e.get();

            // Met à jour le champ 'deBureau' avec la valeur fournie dans l'objet de la requête
            current.setDeBureau(ordinateur.getDeBureau());

            // Sauvegarde l'ordinateur mis à jour et retourne l'objet modifié
            return ordinateurService.saveOrdinateur(current);
        } else {
            // Si l'ordinateur n'est pas trouvé, retourne null (possibilité d'améliorer la gestion en retournant une réponse 404)
            return null;
        }
    }

    /**
     * Supprime un ordinateur en fonction de son identifiant.
     * <p>
     * Ce endpoint accepte une requête HTTP DELETE et supprime l'ordinateur correspondant
     * à l'id passé en paramètre d'URL.
     * </p>
     *
     * @param id L'identifiant de l'ordinateur à supprimer.
     */
    @DeleteMapping("/ordinateur/{id}")
    public void deleteOrdinateur(@PathVariable int id) {
        // Appelle le service pour supprimer l'ordinateur correspondant à l'id fourni
        ordinateurService.deleteOrdinateur(id);
    }
}
