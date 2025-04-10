package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents a peripheral device in the system.
 * This entity is mapped to the "peripheriques" table in the database.
 */
@Data
@Entity
@Table(name = "peripheriques")
public class Peripherique {

    /**
     * The unique identifier for the peripheral device.
     * This field is mapped to the "idAppareil" column in the database.
     */
    @Id
    @Column(name = "idAppareil")
    private long idAppareil;

    /**
     * The type of the peripheral device.
     * This field is mapped to the "type" column and cannot be null.
     */
    @Column(name = "type", nullable = false)
    private TypePeripherique type;

    /**
     * The associated Appareil entity.
     * This relationship is one-to-one and uses the same ID as the peripheral device.
     * Cascade operations include persist and remove.
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    @JoinColumn(name = "idAppareil", referencedColumnName = "idAppareil")
    private Appareil appareil;
}
