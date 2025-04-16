package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.AppareilRepository;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PersonneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PersonneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private AppareilRepository appareilRepository;

    private Personne savedPersonne;
    private Appareil savedAppareil;

    @BeforeEach
    void setup() {
        // Create and save a person and an appareil for testing purposes
        Personne personne = new Personne();
        personne.setNom("Laroche");
        personne.setPrenom("Pierre");
        personne.setAdresse("15 rue des Roches, 57000 Metz");
        personne.setTelephone("0387125678");
        personne.setDateNaissance(LocalDate.of(1975, 4, 20));
        savedPersonne = personneRepository.save(personne);

        Appareil appareil = new Appareil();
        appareil.setLibelle("test");
        savedAppareil = appareilRepository.save(appareil);

        // Link the savedAppareil to the savedPersonne for testing
        savedPersonne.getAppareils().add(savedAppareil);
        savedPersonne = personneRepository.save(savedPersonne);

        // Create additional Appareil entities for testing
        Appareil appareil1 = new Appareil();
        appareil1.setLibelle("Appareil 1");
        appareilRepository.save(appareil1);

        Appareil appareil10 = new Appareil();
        appareil10.setLibelle("Appareil 10");
        appareilRepository.save(appareil10);

        Appareil appareil12 = new Appareil();
        appareil12.setLibelle("Appareil 12");
        appareilRepository.save(appareil12);
    }

    @AfterEach
    void tearDown() {
        if (this.savedPersonne != null) {
            try {
                // Clear associations and save changes
                this.savedPersonne.getAppareils().clear();
                this.personneRepository.saveAndFlush(this.savedPersonne);

                // Delete the Personne entity
                this.personneRepository.deleteById(this.savedPersonne.getId());
                this.personneRepository.flush();
            } catch (Exception e) {
                System.err.println("Error during tearDown for Personne: " + e.getMessage());
            }
        }

        if (this.savedAppareil != null) {
            try {
                // Delete the Appareil entity
                this.appareilRepository.deleteById(this.savedAppareil.getId());
                this.appareilRepository.flush();
            } catch (Exception e) {
                System.err.println("Error during tearDown for Appareil: " + e.getMessage());
            }
        }

        // Delete additional Appareil entities created during setup
        deleteAppareilByLibelle("Appareil 1");
        deleteAppareilByLibelle("Appareil 10");
        deleteAppareilByLibelle("Appareil 12");
    }

    private void deleteAppareilByLibelle(String libelle) {
        appareilRepository.getAppareilByLibelle(libelle).ifPresent(appareil -> {
            if (appareilRepository.existsById(appareil.getId())) {
                try {
                    appareilRepository.deleteById(appareil.getId());
                    appareilRepository.flush();
                } catch (Exception e) {
                    System.err.println("Error during tearDown for Appareil with libelle " + libelle + ": " + e.getMessage());
                }
            }
        });
    }

    @Transactional
    @Test
    void createPersonne_WhenValidRequest_ReturnsCreated() throws Exception {
        mockMvc.perform(post("/personne")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idAppareil\":\"0\","
                                + "\"nom\":\"Laroche\","
                                + "\"prenom\":\"Pierre\","
                                + "\"adresse\":\"15 rue des Roches, 57000 Metz\","
                                + "\"telephone\":\"0387125678\","
                                + "\"dateNaissance\":\"1975-04-20\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllPersonne() throws Exception {
        mockMvc.perform(get("/personnes"))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonneById() throws Exception {
        mockMvc.perform(get("/personne/" + savedPersonne.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Laroche")))
                .andExpect(jsonPath("$.prenom", is("Pierre")));
    }

    @Transactional
    @Test
    void updatePersonne_WhenValidRequest_ReturnsOk() throws Exception {
        mockMvc.perform(put("/personne/" + savedPersonne.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idAppareil\":\"0\","
                                + "\"nom\":\"Kuntz\","
                                + "\"prenom\":\"Pierre\","
                                + "\"adresse\":\"15 rue des Roches, 57000 Metz\","
                                + "\"telephone\":\"0387125678\","
                                + "\"dateNaissance\":\"1975-04-18\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Kuntz")));
    }

    @Transactional
    @Test
    void deletePersonne_WhenValidId_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/personne/" + savedPersonne.getId()))
                .andExpect(status().isOk());
    }

    @Transactional
    @Test
    void addAppareilToPersonne_WhenValidIds_ReturnsUpdatedPersonne() throws Exception {
        mockMvc.perform(put("/personne/" + savedPersonne.getId() + "/appareil/" + savedAppareil.getId()))
                .andExpect(status().isOk());
    }

    @Transactional
    @Test
    void removeAppareilToPersonne_WhenValidIds_ReturnsUpdatedPersonne() throws Exception {
        mockMvc.perform(delete("/personne/" + savedPersonne.getId() + "/appareil/" + savedAppareil.getId()))
                .andExpect(status().isOk());
    }

    @Transactional
    @Test
    void testAddAppareilToPersonne_WhenValidRequest_ReturnsUpdatedPersonne() throws Exception {
        // Ensure the Appareil entities exist before running the test
        Appareil appareil1 = appareilRepository.getAppareilByLibelle("Appareil 1").orElseThrow(() -> new RuntimeException("Appareil 1 not found"));
        Appareil appareil10 = appareilRepository.getAppareilByLibelle("Appareil 10").orElseThrow(() -> new RuntimeException("Appareil 10 not found"));
        Appareil appareil12 = appareilRepository.getAppareilByLibelle("Appareil 12").orElseThrow(() -> new RuntimeException("Appareil 12 not found"));

        mockMvc.perform(put("/personne/" + savedPersonne.getId() + "/appareils")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"" + appareil1.getId() + "\",\"" + appareil10.getId() + "\",\"" + appareil12.getId() + "\"]"))
                .andExpect(status().isOk());

        this.savedPersonne.getAppareils().clear();
        this.personneRepository.save(this.savedPersonne);
    }
}