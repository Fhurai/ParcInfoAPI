package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name= "appareils")
public class Appareil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idAppareil")
    private long id;

    @Column(name= "libelle")
    private String libelle;

}
