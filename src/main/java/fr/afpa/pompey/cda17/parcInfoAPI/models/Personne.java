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

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "personnes")
public class Personne {

    /**
     * Identifiant unique généré automatiquement pour la personne.
     * Utilise une stratégie de génération automatique définie par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * Liste des appareils électroniques associés à la personne.
     * <p>
     * Relation ManyToMany : une personne peut posséder plusieurs appareils,
     * et un appareil peut appartenir à plusieurs personnes.
     * Le chargement EAGER signifie que tous les appareils sont récupérés
     * immédiatement avec les données de la personne (peut impacter les performances).
     * </p>
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Appareil> appareils;

    public boolean addAppareil(Appareil appareil){
        return appareils.add(appareil);
    }

    public boolean removeAppareil(Appareil appareil){
        return appareils.remove(appareil);
    }
}