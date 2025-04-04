package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Représente un appareil dans le système ParcInfo.
 * <p>
 * Cette classe est mappée sur la table <em>appareils</em> dans la base de données.
 * Elle utilise la stratégie d'héritage <em>JOINED</em> afin que les classes dérivées
 * puissent étendre cette entité et stocker leurs attributs spécifiques dans des tables
 * séparées tout en partageant un identifiant commun.
 * </p>
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "appareils")
public class Appareil {

    /**
     * L'identifiant unique de l'appareil.
     * <p>
     * Cet identifiant est généré automatiquement par la base de données
     * en utilisant la stratégie <em>IDENTITY</em>.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAppareil")
    private long id;

    /**
     * Le libellé de l'appareil.
     * <p>
     * Représente le nom ou l'intitulé de l'appareil.
     * </p>
     */
    @Column(name = "libelle")
    private String libelle;
}

