package tn.esprit.tpfoyer;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class BlocServiceImplTest {

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    private Bloc bloc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the Bloc object
        bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc 1");
        bloc.setCapaciteBloc(100);
    }

    @Test
    void testRetrieveAllBlocs() {
        // Prepare mock data
        List<Bloc> mockBlocs = new ArrayList<>();
        mockBlocs.add(bloc);

        when(blocRepository.findAll()).thenReturn(mockBlocs);

        // Call the service method
        List<Bloc> blocs = blocService.retrieveAllBlocs();

        // Verify the result
        assertNotNull(blocs);
        assertEquals(1, blocs.size());
        assertEquals("Bloc 1", blocs.get(0).getNomBloc());
    }

    @Test
    void testRetrieveBlocsSelonCapacite() {
        // Prepare mock data
        List<Bloc> mockBlocs = new ArrayList<>();
        mockBlocs.add(bloc);

        when(blocRepository.findAll()).thenReturn(mockBlocs);

        // Call the service method
        List<Bloc> blocs = blocService.retrieveBlocsSelonCapacite(50);

        // Verify the result
        assertNotNull(blocs);
        assertEquals(1, blocs.size());
        assertEquals("Bloc 1", blocs.get(0).getNomBloc());
    }

    @Test
    void testRetrieveBloc() {
        // Prepare mock data
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        // Call the service method
        Bloc retrievedBloc = blocService.retrieveBloc(1L);

        // Verify the result
        assertNotNull(retrievedBloc);
        assertEquals("Bloc 1", retrievedBloc.getNomBloc());
    }

    @Test
    void testAddBloc() {
        // Prepare mock behavior for save
        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);

        // Call the service method
        Bloc savedBloc = blocService.addBloc(bloc);

        // Verify the result
        assertNotNull(savedBloc);
        assertEquals("Bloc 1", savedBloc.getNomBloc());
    }

    @Test
    void testModifyBloc() {
        // Prepare mock behavior for save
        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);

        // Call the service method
        Bloc modifiedBloc = blocService.modifyBloc(bloc);

        // Verify the result
        assertNotNull(modifiedBloc);
        assertEquals("Bloc 1", modifiedBloc.getNomBloc());
    }

    @Test
    void testRemoveBloc() {
        // Verify that deleteById is called
        doNothing().when(blocRepository).deleteById(1L);

        // Call the service method
        blocService.removeBloc(1L);

        // Verify the delete method was called once
        verify(blocRepository, times(1)).deleteById(1L);
    }

    @Test
    void testTrouverBlocsSansFoyer() {
        // Prepare mock data
        List<Bloc> mockBlocs = new ArrayList<>();
        mockBlocs.add(bloc);

        when(blocRepository.findAllByFoyerIsNull()).thenReturn(mockBlocs);

        // Call the service method
        List<Bloc> blocsSansFoyer = blocService.trouverBlocsSansFoyer();

        // Verify the result
        assertNotNull(blocsSansFoyer);
        assertEquals(1, blocsSansFoyer.size());
        assertEquals("Bloc 1", blocsSansFoyer.get(0).getNomBloc());
    }

    @Test
    void testTrouverBlocsParNomEtCap() {
        // Prepare mock data
        List<Bloc> mockBlocs = new ArrayList<>();
        mockBlocs.add(bloc);

        when(blocRepository.findAllByNomBlocAndCapaciteBloc(anyString(), anyLong())).thenReturn(mockBlocs);

        // Call the service method
        List<Bloc> blocs = blocService.trouverBlocsParNomEtCap("Bloc 1", 100);

        // Verify the result
        assertNotNull(blocs);
        assertEquals(1, blocs.size());
        assertEquals("Bloc 1", blocs.get(0).getNomBloc());
    }
}
