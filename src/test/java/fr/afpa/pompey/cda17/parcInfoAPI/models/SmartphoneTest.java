package fr.afpa.pompey.cda17.parcInfoAPI.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
//@SpringBootTest
public class SmartphoneTest {
    @Test
    void setAndGetEstSmartphone() {
 Smartphone smartphone = new Smartphone();
 boolean expectedEstSmartphone = true;
 smartphone.setEstSmartphone(expectedEstSmartphone);
 assertEquals(expectedEstSmartphone, smartphone.isEstSmartphone());
    }



    @Test
    void getAppareil(){
        Appareil appareil = new Appareil();
        appareil.setId(1);
        Smartphone smartphone = new Smartphone();
        smartphone.setAppareil(appareil);
        assertEquals(appareil, smartphone.getAppareil());
    }

    @Test
    void testSetAndGetIdappareil() {
        Smartphone smartphone = new Smartphone();
        smartphone.setIdAppareil(42);
        assertEquals(42, smartphone.getIdAppareil());
    }
}
