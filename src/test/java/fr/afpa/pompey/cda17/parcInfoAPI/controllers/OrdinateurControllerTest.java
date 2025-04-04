//package fr.afpa.pompey.cda17.parcInfoAPI.services;
//
//import fr.afpa.pompey.cda17.parcInfoAPI.models.Ordinateur;
//import fr.afpa.pompey.cda17.parcInfoAPI.repositories.OrdinateurRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
///**
// * Classe de test pour le service {@link OrdinateurService}.
// * <p>
// * Cette classe teste les méthodes du service en simulant le comportement du repository
// * grâce à Mockito.
// * </p>
// */
//@ExtendWith(MockitoExtension.class)
//public class OrdinateurControllerTest {
//
//    /**
//     * Simule le repository d'ordinateurs.
//     */
//    @Mock
//    private OrdinateurRepository ordinateurRepository;
//
//    /**
//     * Le service à tester, injecté avec le repository simulé.
//     */
//    @InjectMocks
//    private OrdinateurService ordinateurService;
//
//    private Ordinateur ordinateur;
//
//    /**
//     * Initialise un objet {@link Ordinateur} avant chaque test.
//     */
//    @BeforeEach
//    public void setUp() {
//        ordinateur = new Ordinateur();
//        // On initialise ici l'identifiant partagé par exemple à 1L
//        ordinateur.setIdAppareil(1L);
//    }
//
//    /**
//     * Teste la récupération d'un ordinateur existant par son ID.
//     */
//    @Test
//    public void testGetOrdinateurById_found() {
//        // Simulation du comportement du repository
//        when(ordinateurRepository.findById(1L)).thenReturn(Optional.of(ordinateur));
//
//        // Appel de la méthode à tester
//        Optional<Ordinateur> result = ordinateurService.getOrdinateurById(1L);
//
//        // Vérifications
//        assertTrue(result.isPresent(), "L'ordinateur doit être présent");
//        assertEquals(1L, result.get().getIdAppareil(), "L'ID de l'ordinateur doit être égal à 1");
//        verify(ordinateurRepository).findById(1L);
//    }
//
//    /**
//     * Teste la récupération d'un ordinateur inexistant par son ID.
//     */
//    @Test
//    public void testGetOrdinateurById_notFound() {
//        // Simulation du comportement du repository
//        when(ordinateurRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Appel de la méthode à tester
//        Optional<Ordinateur> result = ordinateurService.getOrdinateurById(1L);
//
//        // Vérifications
//        assertFalse(result.isPresent(), "Aucun ordinateur ne doit être trouvé");
//        verify(ordinateurRepository).findById(1L);
//    }
//
//    /**
//     * Teste la récupération de tous les ordinateurs.
//     */
//    @Test
//    public void testGetOrdinateurs() {
//        // Création d'une liste d'ordinateurs pour simuler des données
//        List<Ordinateur> ordinateurs = Arrays.asList(ordinateur, new Ordinateur());
//        when(ordinateurRepository.findAll()).thenReturn(ordinateurs);
//
//        // Appel de la méthode à tester
//        Iterable<Ordinateur> result = ordinateurService.getOrdinateurs();
//
//        // Vérifications
//        assertNotNull(result, "Le résultat ne doit pas être null");
//        // Convertir Iterable en liste pour simplifier la vérification
//        List<Ordinateur> resultList = (List<Ordinateur>) result;
//        assertEquals(2, resultList.size(), "La taille de la liste doit être de 2");
//        verify(ordinateurRepository).findAll();
//    }
//
//    /**
//     * Teste la suppression d'un ordinateur.
//     */
//    @Test
//    public void testDeleteOrdinateur() {
//        // Appel de la méthode de suppression
//        ordinateurService.deleteOrdinateur(1L);
//
//        // Vérifie que la méthode deleteById du repository a été appelée avec l'ID 1
//        verify(ordinateurRepository).deleteById(1L);
//    }
//
//    /**
//     * Teste la sauvegarde d'un ordinateur.
//     */
//    @Test
//    public void testSaveOrdinateur() {
//        // Simulation du comportement du repository lors de la sauvegarde
//        when(ordinateurRepository.save(ordinateur)).thenReturn(ordinateur);
//
//        // Appel de la méthode à tester
//        Ordinateur savedOrdinateur = ordinateurService.saveOrdinateur(ordinateur);
//
//        // Vérifications
//        assertNotNull(savedOrdinateur, "L'ordinateur sauvegardé ne doit pas être null");
//        assertEquals(ordinateur.getIdAppareil(), savedOrdinateur.getIdAppareil(), "Les IDs doivent correspondre");
//        verify(ordinateurRepository).save(ordinateur);
//    }
//}
