package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name= "appareils")
public class Appareil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private long id;

    @Column(name= "libelle", nullable = false, unique = true, length = 30)
    private String libelle;
}
