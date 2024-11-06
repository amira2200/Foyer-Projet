package tn.esprit.tpfoyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class EtudiantMockitoTest {
    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllEtudiants_shouldReturnListOfEtudiants() {
        // Arrange
        Etudiant etudiant1 = new Etudiant(1L, "John", "Doe", 12345678, new Date(), null);
        Etudiant etudiant2 = new Etudiant(2L, "Jane", "Smith", 87654321, new Date(), null);
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        // Act
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        // Assert
        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void retrieveEtudiant_shouldReturnEtudiantWhenExists() {
        // Arrange
        Long etudiantId = 1L;
        Etudiant etudiant = new Etudiant(etudiantId, "John", "Doe", 12345678, new Date(), null);
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant));

        // Act
        Etudiant result = etudiantService.retrieveEtudiant(etudiantId);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).findById(etudiantId);
    }

    @Test
    void retrieveEtudiant_shouldReturnNullWhenDoesNotExist() {
        // Arrange
        Long etudiantId = 1L;
        when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.empty());

        // Act
        Etudiant result = etudiantService.retrieveEtudiant(etudiantId);

        // Assert
        assertNull(result);
        verify(etudiantRepository, times(1)).findById(etudiantId);
    }

    @Test
    void addEtudiant_shouldSaveEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", 12345678, new Date(), null);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.addEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void modifyEtudiant_shouldUpdateEtudiant() {
        // Arrange
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", 12345678, new Date(), null);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.modifyEtudiant(etudiant);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void removeEtudiant_shouldDeleteEtudiantById() {
        // Arrange
        Long etudiantId = 1L;

        // Act
        etudiantService.removeEtudiant(etudiantId);

        // Assert
        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    void recupererEtudiantParCin_shouldReturnEtudiantWhenCinExists() {
        // Arrange
        long cin = 12345678L;
        Etudiant etudiant = new Etudiant(1L, "John", "Doe", cin, new Date(), null);
        when(etudiantRepository.findEtudiantByCinEtudiant(cin)).thenReturn(etudiant);

        // Act
        Etudiant result = etudiantService.recupererEtudiantParCin(cin);

        // Assert
        assertNotNull(result);
        assertEquals(cin, result.getCinEtudiant());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(cin);
    }

}
