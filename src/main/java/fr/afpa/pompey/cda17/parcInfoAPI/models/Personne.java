package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "personnes")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPersonne")
    private long id;

    @Column(name = "nom", nullable = false, length = 30)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 25)
    private String prenom;

    @Column(name = "adresse", nullable = false, length = 50)
    private String adresse;

    @Column(name = "telephone", nullable = false, length = 15)
    private String telephone;

    @Column(name = "dateNaissance", nullable = false)
    private LocalDate dateNaissance;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Appareil> appareils;

}
