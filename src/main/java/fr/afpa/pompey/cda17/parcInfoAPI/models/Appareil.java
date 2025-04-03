package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name= "appareils")
public class Appareil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "idAppareil")
    private long id;

    @Column(name= "libelle", nullable = false, length = 30)
    private String libelle;
}
