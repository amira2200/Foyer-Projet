package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1L, "Université de Tunis", "Tunis", null));
        universites.add(new Universite(2L, "Université de Sfax", "Sfax", null));

        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite(1L, "Université de Tunis", "Tunis", null);
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1L);

        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite(1L, "Université de Tunis", "Tunis", null);
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testModifyUniversite() {
        Universite universite = new Universite(1L, "Université de Tunis", "Tunis", null);
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.modifyUniversite(universite);

        assertNotNull(result);
        assertEquals("Université de Tunis", result.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRemoveUniversite() {
        Long universiteId = 1L;

        universiteService.removeUniversite(universiteId);

        verify(universiteRepository, times(1)).deleteById(universiteId);
    }
}
