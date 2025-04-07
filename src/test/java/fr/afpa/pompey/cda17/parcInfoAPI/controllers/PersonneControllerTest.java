package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.AppareilRepository;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PersonneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private AppareilRepository appareilRepository;

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
        mockMvc.perform(get("/personne/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Kuntz")));
    }

    @Test
    void updatePersonne_WhenValidRequest_ReturnsOk() throws Exception {
        Personne personne = new Personne();
        personne.setNom("Laroche");
        personne.setPrenom("Pierre");
        personne.setAdresse("15 rue des Roches, 57000 Metz");
        personne.setTelephone("0387125678");
        personne.setDateNaissance(LocalDate.of(1975, 4, 20));
        personneRepository.save(personne);

        mockMvc.perform(put("/personne/" + personne.getId())
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

    @Test
    void deletePersonne_WhenValidId_ReturnsOk() throws Exception {
        Personne personne = new Personne();
        personne.setNom("Laroche");
        personne.setPrenom("Pierre");
        personne.setAdresse("15 rue des Roches, 57000 Metz");
        personne.setTelephone("0387125678");
        personne.setDateNaissance(LocalDate.of(1975, 4, 20));
        personneRepository.save(personne);

        mockMvc.perform(delete("/personne/" + personne.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void addAppareilToPersonne_WhenValidIds_ReturnsUpdatedPersonne() throws Exception {
        Personne personne = new Personne();
        personne.setNom("Laroche");
        personne.setPrenom("Pierre");
        personne.setAdresse("15 rue des Roches, 57000 Metz");
        personne.setTelephone("0387125678");
        personne.setDateNaissance(LocalDate.of(1975, 4, 20));
        personneRepository.save(personne);

        Appareil appareil = new Appareil();
        appareil.setLibelle("test");
        appareilRepository.save(appareil);

        mockMvc.perform(put("/personne/" + personne.getId() + "/appareil/" + appareil.getId()))
                .andExpect(status().isOk());

        personneRepository.delete(personne);
        appareilRepository.delete(appareil);

    }

    @Test
    void removeAppareilToPersonne_WhenValidIds_ReturnsUpdatedPersonne() throws Exception {
        Appareil appareil = new Appareil();
        appareil.setLibelle("test");
        appareilRepository.save(appareil);

        Personne personne = new Personne();
        personne.setNom("Laroche");
        personne.setPrenom("Pierre");
        personne.setAdresse("15 rue des Roches, 57000 Metz");
        personne.setTelephone("0387125678");
        personne.setDateNaissance(LocalDate.of(1975, 4, 20));
        personne.getAppareils().add(appareil);
        personneRepository.save(personne);

        mockMvc.perform(delete("/personne/" + personne.getId() + "/appareil/" + appareil.getId()))
                .andExpect(status().isOk());

        personneRepository.delete(personne);
        appareilRepository.delete(appareil);
    }

    @Test
    void testAddAppareilToPersonne_WhenValidRequest_ReturnsUpdatedPersonne() throws Exception {
        Personne personne = new Personne();
        personne.setNom("Laroche");
        personne.setPrenom("Pierre");
        personne.setAdresse("15 rue des Roches, 57000 Metz");
        personne.setTelephone("0387125678");
        personne.setDateNaissance(LocalDate.of(1975, 4, 20));
        personneRepository.save(personne);

        mockMvc.perform(put("/personne/" + personne.getId() + "/appareils")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[\"1\",\"10\",\"12\"]"))
                .andExpect(status().isOk());
    }
}