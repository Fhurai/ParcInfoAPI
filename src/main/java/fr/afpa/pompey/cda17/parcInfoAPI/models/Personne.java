/**
 * Classe représentant une personne dans le système.
 * <p>
 * Cette entité JPA est mappée sur la table "personnes" de la base de données.
 * Elle utilise Lombok pour générer automatiquement les méthodes getters, setters, toString, etc.
 * </p>
 *
 * @author Pompey CDA17
 * @version 1.0
 */
package fr.afpa.pompey.cda17.parcInfoAPI.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "personnes")
public class Personne {

    /**
     * Identifiant unique auto-généré de l'appareil.
     * <p>
     * Stratégie de génération automatique configurée avec la stratégie IDENTITY
     * pour la compatibilité avec la plupart des bases de données relationnelles.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonne")
    private long id;

    /**
     * Nom de famille de la personne.
     * Contrainte : obligatoire, longueur maximale de 30 caractères.
     */
    @Column(name = "nom", nullable = false, length = 30)
    private String nom;

    /**
     * Prénom de la personne.
     * Contrainte : obligatoire, longueur maximale de 25 caractères.
     */
    @Column(name = "prenom", nullable = false, length = 25)
    private String prenom;

    /**
     * Adresse postale complète de la personne.
     * Contrainte : obligatoire, longueur maximale de 50 caractères.
     */
    @Column(name = "adresse", nullable = false, length = 50)
    private String adresse;

    /**
     * Numéro de téléphone de la personne.
     * Contrainte : obligatoire, format libre (ex: +33 6 12 34 56 78), longueur maximale de 15 caractères.
     */
    @Column(name = "telephone", nullable = false, length = 15)
    private String telephone;

    /**
     * Date de naissance de la personne au format ISO (AAAA-MM-JJ).
     * Contrainte : obligatoire.
     */
    @Column(name = "dateNaissance", nullable = false)
    private LocalDate dateNaissance;

    /**
     * Liste des appareils associés à la personne.
     * <p>
     * Configuration de la relation :
     * - Chargement immédiat (EAGER) → À utiliser avec précaution
     * - Cascade MERGE seulement → Sécurité des opérations
     * - Table de jointure implicite (personne_appareils)
     * </p>
     *
     * @implNote Une relation bidirectionnelle nécessiterait d'ajouter dans Appareil :
     * @ManyToMany(mappedBy = "appareils")
     * private List<Personne> proprietaires;
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    /**
     * Annotation utilisée pour éviter les boucles infinies lors de la sérialisation JSON.
     * Elle indique que cette relation est la "partie inverse" dans une relation bidirectionnelle.
     */
    @JsonBackReference
    private List<Appareil> appareils = new ArrayList<>();
}