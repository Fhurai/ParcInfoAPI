package fr.afpa.pompey.cda17.parcInfoAPI.controllers;

import fr.afpa.pompey.cda17.parcInfoAPI.models.Appareil;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the PeripheriqueController.
 * This class contains unit tests for the endpoints of the PeripheriqueController.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PeripheriqueControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private PeripheriqueRepository peripheriqueRepository;

   private Peripherique savedPeripherique;

   /**
    * Sets up a test Peripherique entity before each test.
    * This ensures that a valid Peripherique exists in the repository for testing.
    */
   @BeforeEach
   void setup() {
       // Set up a test peripherique entity to be used in the tests
       Peripherique peripherique = new Peripherique();
       peripherique.setAppareil(new Appareil());
       peripherique.getAppareil().setLibelle("Clavier");
       peripherique.setType(TypePeripherique.CLAVIER);
       savedPeripherique = peripheriqueRepository.save(peripherique);
   }

   /**
    * Cleans up the test data after each test to avoid side effects between tests.
    */
   @AfterEach
   void tearDown() {
       // Clean up the test data to ensure no side effects between tests
       if (savedPeripherique != null) peripheriqueRepository.delete(savedPeripherique);
   }

   /**
    * Tests the creation of a new Peripherique with valid input.
    * Verifies that the response status is 201 Created.
    */
   @Test
   void createPeripherique_WhenValidRequest_ReturnsCreated() throws Exception {
       // Test the creation of a new peripherique with valid input
       mockMvc.perform(post("/peripherique")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"type\":\"SOURIS\", " +
                               "\"appareil\":{\"libelle\": \"\"}}"))
               .andExpect(status().isCreated());
   }

   /**
    * Tests retrieving all Peripheriques.
    * Verifies that the response status is 200 OK.
    */
   @Test
   void getAllPeripheriques() throws Exception {
       // Test retrieving all peripheriques
       mockMvc.perform(get("/peripheriques"))
               .andExpect(status().isOk());
   }

   /**
    * Tests retrieving a specific Peripherique by its ID.
    * Verifies that the response status is 200 OK and the returned data matches the expected Peripherique.
    */
   @Test
   void getPeripheriqueById() throws Exception {
       // Test retrieving a specific peripherique by its ID
       mockMvc.perform(get("/peripherique/" + savedPeripherique.getIdAppareil()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.appareil.libelle", is("Clavier")));
   }

   /**
    * Tests updating an existing Peripherique with valid input.
    * Verifies that the response status is 200 OK and the updated data is correct.
    */
   @Test
   void updatePeripherique_WhenValidRequest_ReturnsOk() throws Exception {
       // Test updating an existing peripherique with valid input
       mockMvc.perform(put("/peripherique/" + savedPeripherique.getIdAppareil())
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"type\":\"CLAVIER\", " +
                               "\"appareil\":{\"libelle\": \"Souris\"}}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.appareil.libelle", is("Souris")));
   }

   /**
    * Tests deleting a Peripherique by its ID.
    * Verifies that the response status is 200 OK.
    */
   @Test
   void deletePeripherique_WhenValidId_ReturnsOk() throws Exception {
       // Test deleting a peripherique by its ID
       mockMvc.perform(delete("/peripherique/" + savedPeripherique.getIdAppareil()))
               .andExpect(status().isOk());
   }
}
