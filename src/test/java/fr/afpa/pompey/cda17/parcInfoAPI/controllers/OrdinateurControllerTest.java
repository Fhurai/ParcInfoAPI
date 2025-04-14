package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@SpringBootTest
public class OrdinateurControllerTest {
    @Test
    void setAndGetEstOrdinateur() {
        Ordinateur ordinateur = new Ordinateur();
        boolean expectedDeBureau = true;
        ordinateur.setDeBureau(expectedDeBureau);
        assertEquals(expectedDeBureau, ordinateur.getDeBureau());
    }

    @Test
    void getAppareil() {
        Appareil appareil = new Appareil();
        appareil.setId(1);
        Ordinateur ordinateur = new Ordinateur();
        ordinateur.setAppareil(appareil);
        assertEquals(appareil, ordinateur.getAppareil());
    }

    @Test
    void testSetAndGetIdappareil() {
        Ordinateur ordinateur = new Ordinateur();
        ordinateur.setIdAppareil(42);
        assertEquals(42, ordinateur.getIdAppareil());
    }
}
