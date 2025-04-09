package fr.afpa.pompey.cda17.parcInfoAPI.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppareilTest {

    @Test
    void getId() {
        Appareil appareil = new Appareil();
        long expectedId = 1L;
        appareil.setId(expectedId);
        assertEquals(expectedId, appareil.getId());
    }

    @Test
    void getLibelle() {
        Appareil appareil = new Appareil();
        String expectedLibelle = "Ordinateur";
        appareil.setLibelle(expectedLibelle);
        assertEquals(expectedLibelle, appareil.getLibelle());
    }

    @Test
    void getProprietaires() {
        Appareil appareil = new Appareil();
        Personne personne = new Personne();
        List<Personne> expectedProprietaires = List.of(personne);
        appareil.setProprietaires(expectedProprietaires);
        assertEquals(expectedProprietaires, appareil.getProprietaires());
    }

    @Test
    void setId() {
        Appareil appareil = new Appareil();
        long newId = 2L;
        appareil.setId(newId);
        assertEquals(newId, appareil.getId());
    }

    @Test
    void setLibelle() {
        Appareil appareil = new Appareil();
        String newLibelle = "Imprimante";
        appareil.setLibelle(newLibelle);
        assertEquals(newLibelle, appareil.getLibelle());
    }

    @Test
    void setProprietaires() {
        Appareil appareil = new Appareil();
        List<Personne> newProprietaires = List.of(new Personne(), new Personne());
        appareil.setProprietaires(newProprietaires);
        assertEquals(newProprietaires, appareil.getProprietaires());
    }
}
