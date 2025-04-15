package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
import fr.afpa.pompey.cda17.parcInfoAPI.models.Smartphone;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.AppareilRepository;
import fr.afpa.pompey.cda17.parcInfoAPI.repositories.SmartphoneRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SmartphoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Autowired
    private AppareilRepository appareilRepository;

    private Smartphone savedSmartphone;
    private Appareil savedAppareil;

    @BeforeEach
    void setUp() {
        Appareil appareil = new Appareil();
        appareil.setLibelle("test appareil");
        savedAppareil = appareilRepository.save(appareil);

        Smartphone smartphone = new Smartphone();

        smartphone.setEstSmartphone(true);
        smartphone.setAppareil(savedAppareil);
        savedSmartphone = smartphoneRepository.save(smartphone);

    }

    @AfterEach
    void tearDown() {
        if (savedSmartphone != null)
            smartphoneRepository.delete(savedSmartphone);
        if (savedAppareil != null) appareilRepository.delete(savedAppareil);
    }

    @Transactional
    @Test
    void createSmartphone_WhenValidRequest_ReturnsCreated() throws Exception {
        mockMvc.perform(post("/smartphone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idAppareil\":" + savedAppareil.getId() + ",\"estSmartphone\":true}"))
                .andExpect(status().isCreated());


    }


    @Transactional
    @Test
    void getAllSmartphone() throws Exception {
        mockMvc.perform(get("/smartphones"))
                .andExpect(status().isOk());
    }

    @Transactional
    @Test
    void getSmartphoneByIdAppareil() throws Exception {
        mockMvc.perform(get("/smartphone/" + savedSmartphone.getIdAppareil()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAppareil", is((int) savedSmartphone.getIdAppareil())))
                .andExpect(jsonPath("$.appareil.id",
                        is((int) savedSmartphone.getAppareil().getId())))
                .andExpect(jsonPath("$.estSmartphone", is(savedSmartphone.isEstSmartphone())));

    }
}
