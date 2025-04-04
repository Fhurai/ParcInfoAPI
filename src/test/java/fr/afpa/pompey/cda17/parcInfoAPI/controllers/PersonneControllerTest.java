package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Personne;
import fr.afpa.pompey.cda17.parcInfoAPI.services.PersonneService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonneService personneService;

    @Test
    void createPersonne_WhenValidRequest_ReturnsCreated() throws Exception {
        Personne mockPersonne = new Personne();
        mockPersonne.setId(4L);
        mockPersonne.setNom("Condé");
        Mockito.when(personneService
                .save(Mockito.any(Personne.class)))
                .thenReturn(mockPersonne);

        mockMvc.perform(post("/personne")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Condé\"}"))
                .andExpect(status()
                        .isCreated())
                .andExpect(header()
                        .exists("Location"))
                .andExpect(header()
                        .string("Location",
                                "http://localhost/personne/4"));
    }

    @Test
    void createPersonne_WhenServiceFails_ReturnsNoContent() throws Exception {
        Mockito.when(personneService.save(Mockito.any(Personne.class))).thenReturn(null);

        mockMvc.perform(post("/personne")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Invalid\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllPersonne() throws Exception {
        Personne personne = new Personne();
        personne.setNom("Condé");
        List<Personne> personnes = List.of(personne);
        Mockito.when(personneService.getPersonnes()).thenReturn(personnes);

        mockMvc.perform(get("/personnes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is("Condé")));
    }

    @Test
    void getPersonneById() throws Exception {
        Personne mockPersonne = new Personne();
        mockPersonne.setNom("Kuntz");
        Mockito.when(personneService.getPersonne(2L)).thenReturn(Optional.of(mockPersonne));

        mockMvc.perform(get("/personne/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Kuntz")));
    }

    @Test
    void updatePersonne_WhenValidRequest_ReturnsOk() throws Exception {
        Personne existingPersonne = new Personne();
        existingPersonne.setId(1L);
        existingPersonne.setNom("Old Name");

        Personne updatedPersonne = new Personne();
        updatedPersonne.setId(1L);
        updatedPersonne.setNom("New Name");

        Mockito.when(personneService.getPersonne(1L)).thenReturn(Optional.of(existingPersonne));
        Mockito.when(personneService.save(Mockito.any(Personne.class))).thenReturn(updatedPersonne);

        mockMvc.perform(put("/personne/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"New Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("New Name")));
    }

    @Test
    void updatePersonne_WhenPersonneNotFound_ReturnsNull() throws Exception {
        Mockito.when(personneService.getPersonne(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/personne/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"New Name\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void deletePersonne_WhenValidId_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/personne/1"))
                .andExpect(status().isOk());

        Mockito.verify(personneService, Mockito.times(1)).deletePersonne(1L);
    }

    @Test
    void addAppareilToPersonne_WhenValidIds_ReturnsUpdatedPersonne() throws Exception {
        Personne mockPersonne = new Personne();
        mockPersonne.setId(1L);
        mockPersonne.setNom("Test");

        Mockito.when(personneService.addAppareilToPersonne(1L, 2L)).thenReturn(mockPersonne);

        mockMvc.perform(put("/personnes/1/appareil/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Test")));
    }

    @Test
    void addAppareilToPersonne_WhenServiceReturnsNull_ReturnsOkWithNullBody() throws Exception {
        Mockito.when(personneService.addAppareilToPersonne(1L, 2L)).thenReturn(null);

        mockMvc.perform(put("/personnes/1/appareil/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void removeAppareilToPersonne_WhenValidIds_ReturnsUpdatedPersonne() throws Exception {
        Personne mockPersonne = new Personne();
        mockPersonne.setId(1L);
        mockPersonne.setNom("Test");

        Mockito.when(personneService.removeAppareilFromPersonne(1L, 2L)).thenReturn(mockPersonne);

        mockMvc.perform(delete("/personnes/1/appareil/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Test")));
    }

    @Test
    void removeAppareilToPersonne_WhenServiceReturnsNull_ReturnsOkWithNullBody() throws Exception {
        Mockito.when(personneService.removeAppareilFromPersonne(1L, 2L)).thenReturn(null);

        mockMvc.perform(delete("/personnes/1/appareil/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testAddAppareilToPersonne_WhenValidRequest_ReturnsUpdatedPersonne() throws Exception {
        Personne mockPersonne = new Personne();
        mockPersonne.setId(1L);
        mockPersonne.setNom("Test");

        Mockito.when(personneService.updateAppareilsToPersonne(1L, new String[]{"1", "2"}))
                .thenReturn(mockPersonne);

        mockMvc.perform(put("/personnes/1/appareils")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"1\", \"2\"]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Test")));
    }

    @Test
    void testAddAppareilToPersonne_WhenServiceReturnsNull_ReturnsOkWithNullBody() throws Exception {
        Mockito.when(personneService.updateAppareilsToPersonne(1L, new String[]{"1", "2"}))
                .thenReturn(null);

        mockMvc.perform(put("/personnes/1/appareils")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\"1\", \"2\"]"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}