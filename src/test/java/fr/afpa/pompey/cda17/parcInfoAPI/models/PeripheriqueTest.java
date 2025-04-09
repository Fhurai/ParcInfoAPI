package fr.afpa.pompey.cda17.parcInfoAPI.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeripheriqueTest {

    @Test
    void getIdAppareil() {
        Peripherique peripherique = new Peripherique();
        long expectedId = 1L;
        peripherique.setIdAppareil(expectedId);
        assertEquals(expectedId, peripherique.getIdAppareil());
    }

    @Test
    void getType() {
        Peripherique peripherique = new Peripherique();
        TypePeripherique expectedType = TypePeripherique.CLAVIER;
        peripherique.setType(expectedType);
        assertEquals(expectedType, peripherique.getType());
    }

    @Test
    void getAppareil() {
        Peripherique peripherique = new Peripherique();
        Appareil expectedAppareil = new Appareil();
        peripherique.setAppareil(expectedAppareil);
        assertEquals(expectedAppareil, peripherique.getAppareil());
    }

    @Test
    void setIdAppareil() {
        Peripherique peripherique = new Peripherique();
        long newId = 2L;
        peripherique.setIdAppareil(newId);
        assertEquals(newId, peripherique.getIdAppareil());
    }

    @Test
    void setType() {
        Peripherique peripherique = new Peripherique();
        TypePeripherique newType = TypePeripherique.SOURIS; // Example type
        peripherique.setType(newType);
        assertEquals(newType, peripherique.getType());
    }

    @Test
    void setAppareil() {
        Peripherique peripherique = new Peripherique();
        Appareil newAppareil = new Appareil();
        peripherique.setAppareil(newAppareil);
        assertEquals(newAppareil, peripherique.getAppareil());
    }
}
