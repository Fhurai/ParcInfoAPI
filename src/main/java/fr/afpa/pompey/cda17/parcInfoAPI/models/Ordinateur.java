package fr.afpa.pompey.cda17.parcInfoAPI.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ordinateur")

public class Ordinateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idOrdinateur")
    private int idOrdinateur;

    private Boolean deBureau = true;
}
git