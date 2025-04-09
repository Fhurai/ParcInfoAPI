package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "peripheriques")
public class Peripherique {

    @Id
    @Column(name = "idAppareil")
    private long idAppareil;

    @Column(name = "type", nullable = false)
    private TypePeripherique type;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    @JoinColumn(name = "idAppareil", referencedColumnName = "idAppareil")
    private Appareil appareil;
}
