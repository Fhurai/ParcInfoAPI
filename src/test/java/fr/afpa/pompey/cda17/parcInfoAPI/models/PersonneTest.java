package fr.afpa.pompey.cda17.parcInfoAPI.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonneTest {

    @Test
    void getId() {
        Personne personne = new Personne();
        long expectedId = 5L;
        personne.setId(expectedId);
        assertEquals(expectedId, personne.getId());
    }

    @Test
    void getNom() {
        Personne personne = new Personne();
        String expectedNom = "Dupont";
        personne.setNom(expectedNom);
        assertEquals(expectedNom, personne.getNom());
    }

    @Test
    void getPrenom() {
        Personne personne = new Personne();
        String expectedPrenom = "Jean";
        personne.setPrenom(expectedPrenom);
        assertEquals(expectedPrenom, personne.getPrenom());
    }

    @Test
    void getAdresse() {
        Personne personne = new Personne();
        String expectedAdresse = "123 Rue de Paris";
        personne.setAdresse(expectedAdresse);
        assertEquals(expectedAdresse, personne.getAdresse());
    }

    @Test
    void getTelephone() {
        Personne personne = new Personne();
        String expectedTel = "+33 612345678";
        personne.setTelephone(expectedTel);
        assertEquals(expectedTel, personne.getTelephone());
    }

    @Test
    void getDateNaissance() {
        Personne personne = new Personne();
        LocalDate expectedDate = LocalDate.of(1990, 5, 15);
        personne.setDateNaissance(expectedDate);
        assertEquals(expectedDate, personne.getDateNaissance());
    }

    @Test
    void getAppareils() {
        Personne personne = new Personne();
        Appareil appareil = new Appareil();
        List<Appareil> expectedAppareils = List.of(appareil);
        personne.setAppareils(expectedAppareils);
        assertEquals(expectedAppareils, personne.getAppareils());
    }

    @Test
    void setId() {
        Personne personne = new Personne();
        long newId = 10L;
        personne.setId(newId);
        assertEquals(newId, personne.getId());
    }

    @Test
    void setNom() {
        Personne personne = new Personne();
        String newNom = "Martin";
        personne.setNom(newNom);
        assertEquals(newNom, personne.getNom());
    }

    @Test
    void setPrenom() {
        Personne personne = new Personne();
        String newPrenom = "Marie";
        personne.setPrenom(newPrenom);
        assertEquals(newPrenom, personne.getPrenom());
    }

    @Test
    void setAdresse() {
        Personne personne = new Personne();
        String newAdresse = "456 Avenue Lyon";
        personne.setAdresse(newAdresse);
        assertEquals(newAdresse, personne.getAdresse());
    }

    @Test
    void setTelephone() {
        Personne personne = new Personne();
        String newTel = "+33 698765432";
        personne.setTelephone(newTel);
        assertEquals(newTel, personne.getTelephone());
    }

    @Test
    void setDateNaissance() {
        Personne personne = new Personne();
        LocalDate newDate = LocalDate.of(1985, 10, 20);
        personne.setDateNaissance(newDate);
        assertEquals(newDate, personne.getDateNaissance());
    }

    @Test
    void setAppareils() {
        Personne personne = new Personne();
        List<Appareil> newAppareils = List.of(new Appareil(), new Appareil());
        personne.setAppareils(newAppareils);
        assertEquals(newAppareils, personne.getAppareils());
    }

    // Helper method to create Personne with common fields
    private Personne createSamplePersonne(Long id, String nom, String prenom) {
        Personne p = new Personne();
        p.setId(id);
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setAdresse("123 Rue Example");
        p.setTelephone("+33 600000000");
        p.setDateNaissance(LocalDate.of(1990, 1, 1));
        return p;
    }
}