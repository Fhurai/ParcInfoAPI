package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Représente un ordinateur dans le système ParcInfo.
 * <p>
 * Cette entité est mappée sur la table <em>ordinateurs</em> et utilise une relation One-to-One
 * avec l'entité {@link Appareil}. La clé primaire de cette entité est partagée avec celle de l'objet
 * {@link Appareil} associé grâce à l'annotation {@code @MapsId}. Ainsi, l'identifiant de l'ordinateur
 * est le même que celui de l'appareil.
 * </p>
 */
@Data
@Entity
@Table(name = "ordinateurs")
public class Ordinateur  {

    /**
     * L'identifiant partagé de l'ordinateur.
     * <p>
     * Cet identifiant est dérivé de l'entité {@link Appareil} associée. L'annotation {@code @MapsId}
     * permet de lier la clé primaire de {@code Ordinateur} à celle de {@code Appareil}.
     * </p>
     */
    @Id
    @Column(name = "idAppareil")
    private long idAppareil;

    /**
     * Indique si l'ordinateur est de bureau.
     * <p>
     * Ce champ ne peut pas être nul et permet de déterminer si l'ordinateur est destiné à un usage fixe (bureau).
     * </p>
     */
    @Column(name = "deBureau", nullable = false)
    private Boolean deBureau;

    /**
     * Référence vers l'objet {@link Appareil} associé.
     * <p>
     * La relation One-to-One est configurée pour partager la clé primaire avec l'entité {@link Appareil}
     * grâce à {@code @MapsId}. La cascade inclut {@code CascadeType.PERSIST} pour la persistance et
     * {@code CascadeType.REMOVE} pour la suppression de l'objet {@code Appareil} associé lorsque l'ordinateur est supprimé.
     * </p>
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    @JoinColumn(name = "idAppareil", referencedColumnName = "idAppareil")
    private Appareil appareil;
}

