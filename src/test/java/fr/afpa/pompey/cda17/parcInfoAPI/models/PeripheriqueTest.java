package fr.afpa.pompey.cda17.parcInfoAPI.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeripheriqueTest {

    /**
     * Test to verify the getter for the 'idAppareil' property.
     */
    @Test
    void getIdAppareil() {
        // Create a new Peripherique instance
        Peripherique peripherique = new Peripherique();
        // Set the expected id value
        long expectedId = 1L;
        peripherique.setIdAppareil(expectedId);
        // Assert that the getter returns the expected value
        assertEquals(expectedId, peripherique.getIdAppareil());
    }

    /**
     * Test to verify the getter for the 'type' property.
     */
    @Test
    void getType() {
        // Create a new Peripherique instance
        Peripherique peripherique = new Peripherique();
        // Set the expected type value
        TypePeripherique expectedType = TypePeripherique.CLAVIER;
        peripherique.setType(expectedType);
        // Assert that the getter returns the expected value
        assertEquals(expectedType, peripherique.getType());
    }

    /**
     * Test to verify the getter for the 'appareil' property.
     */
    @Test
    void getAppareil() {
        // Create a new Peripherique instance
        Peripherique peripherique = new Peripherique();
        // Create and set the expected Appareil instance
        Appareil expectedAppareil = new Appareil();
        peripherique.setAppareil(expectedAppareil);
        // Assert that the getter returns the expected Appareil instance
        assertEquals(expectedAppareil, peripherique.getAppareil());
    }

    /**
     * Test to verify the setter for the 'idAppareil' property.
     */
    @Test
    void setIdAppareil() {
        // Create a new Peripherique instance
        Peripherique peripherique = new Peripherique();
        // Set a new id value
        long newId = 2L;
        peripherique.setIdAppareil(newId);
        // Assert that the getter reflects the updated value
        assertEquals(newId, peripherique.getIdAppareil());
    }

    /**
     * Test to verify the setter for the 'type' property.
     */
    @Test
    void setType() {
        // Create a new Peripherique instance
        Peripherique peripherique = new Peripherique();
        // Set a new type value
        TypePeripherique newType = TypePeripherique.SOURIS; // Example type
        peripherique.setType(newType);
        // Assert that the getter reflects the updated value
        assertEquals(newType, peripherique.getType());
    }

    /**
     * Test to verify the setter for the 'appareil' property.
     */
    @Test
    void setAppareil() {
        // Create a new Peripherique instance
        Peripherique peripherique = new Peripherique();
        // Create and set a new Appareil instance
        Appareil newAppareil = new Appareil();
        peripherique.setAppareil(newAppareil);
        // Assert that the getter reflects the updated Appareil instance
        assertEquals(newAppareil, peripherique.getAppareil());
    }
}
