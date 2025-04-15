package fr.afpa.pompey.cda17.parcInfoAPI.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppareilTest {

    /**
     * Test to verify the getter for the 'id' field of the Appareil class.
     */
    @Test
    void getId() {
        // Create an instance of Appareil
        Appareil appareil = new Appareil();
        // Set the expected id
        long expectedId = 1L;
        appareil.setId(expectedId);
        // Assert that the getter returns the expected id
        assertEquals(expectedId, appareil.getId());
    }

    /**
     * Test to verify the getter for the 'libelle' field of the Appareil class.
     */
    @Test
    void getLibelle() {
        // Create an instance of Appareil
        Appareil appareil = new Appareil();
        // Set the expected libelle
        String expectedLibelle = "Ordinateur";
        appareil.setLibelle(expectedLibelle);
        // Assert that the getter returns the expected libelle
        assertEquals(expectedLibelle, appareil.getLibelle());
    }

    /**
     * Test to verify the getter for the 'proprietaires' field of the Appareil class.
     */
    @Test
    void getProprietaires() {
        // Create an instance of Appareil
        Appareil appareil = new Appareil();
        // Create a list of proprietaires
        Personne personne = new Personne();
        List<Personne> expectedProprietaires = List.of(personne);
        // Set the proprietaires
        appareil.setProprietaires(expectedProprietaires);
        // Assert that the getter returns the expected proprietaires
        assertEquals(expectedProprietaires, appareil.getProprietaires());
    }

    /**
     * Test to verify the setter for the 'id' field of the Appareil class.
     */
    @Test
    void setId() {
        // Create an instance of Appareil
        Appareil appareil = new Appareil();
        // Set a new id
        long newId = 2L;
        appareil.setId(newId);
        // Assert that the getter returns the new id
        assertEquals(newId, appareil.getId());
    }

    /**
     * Test to verify the setter for the 'libelle' field of the Appareil class.
     */
    @Test
    void setLibelle() {
        // Create an instance of Appareil
        Appareil appareil = new Appareil();
        // Set a new libelle
        String newLibelle = "Imprimante";
        appareil.setLibelle(newLibelle);
        // Assert that the getter returns the new libelle
        assertEquals(newLibelle, appareil.getLibelle());
    }

    /**
     * Test to verify the setter for the 'proprietaires' field of the Appareil class.
     */
    @Test
    void setProprietaires() {
        // Create an instance of Appareil
        Appareil appareil = new Appareil();
        // Create a new list of proprietaires
        List<Personne> newProprietaires = List.of(new Personne(), new Personne());
        // Set the new proprietaires
        appareil.setProprietaires(newProprietaires);
        // Assert that the getter returns the new proprietaires
        assertEquals(newProprietaires, appareil.getProprietaires());
    }
}
