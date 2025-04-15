package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="smartphones")
public class Smartphone {
    @Id
    @Column(name = "idAppareil")
    private long idAppareil;


    @Column(name = "estSmartphone", nullable = false)
    private boolean estSmartphone;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    @JoinColumn( name = "idAppareil", referencedColumnName = "idAppareil")
    private Appareil appareil;


}
