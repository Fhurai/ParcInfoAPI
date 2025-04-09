package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Peripherique;
import fr.afpa.pompey.cda17.parcInfoAPI.models.TypePeripherique;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.PeripheriqueRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PeripheriqueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PeripheriqueRepository peripheriqueRepository;

    private Peripherique savedPeripherique;

    @BeforeEach
    void setup() {
        // Create and save a peripherique for testing purposes
        Peripherique peripherique = new Peripherique();
        peripherique.getAppareil().setLibelle("Clavier");
        peripherique.setType(TypePeripherique.CLAVIER);
        savedPeripherique = peripheriqueRepository.save(peripherique);
    }

    @AfterEach
    void tearDown() {
        // Clean up after each test to avoid side effects
        if (savedPeripherique != null) peripheriqueRepository.delete(savedPeripherique);
    }

    @Test
    void createPeripherique_WhenValidRequest_ReturnsCreated() throws Exception {
        mockMvc.perform(post("/peripherique")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"libelle\":\"Souris\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllPeripheriques() throws Exception {
        mockMvc.perform(get("/peripheriques"))
                .andExpect(status().isOk());
    }

    @Test
    void getPeripheriqueById() throws Exception {
        mockMvc.perform(get("/peripherique/" + savedPeripherique.getIdAppareil()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle", is("Clavier")));
    }

    @Test
    void updatePeripherique_WhenValidRequest_ReturnsOk() throws Exception {
        mockMvc.perform(put("/peripherique/" + savedPeripherique.getIdAppareil())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"libelle\":\"Souris\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle", is("Souris")));
    }

    @Test
    void deletePeripherique_WhenValidId_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/peripherique/" + savedPeripherique.getIdAppareil()))
                .andExpect(status().isOk());
    }
}
