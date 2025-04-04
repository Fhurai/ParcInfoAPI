package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ordinateurs")
public class Ordinateur  {


    @Id
    @Column(name = "idAppareil")
    private long idAppareil;

    @Column(name = "deBureau", nullable = false)
    private Boolean deBureau;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    @JoinColumn(name = "idAppareil", referencedColumnName = "idAppareil")
    private Appareil appareil;



}
