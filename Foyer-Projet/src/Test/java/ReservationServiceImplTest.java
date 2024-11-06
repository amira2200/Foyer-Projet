

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllReservations() {
        List<Reservation> mockReservations = List.of(new Reservation("1", new Date(), true, null));
        when(reservationRepository.findAll()).thenReturn(mockReservations);

        List<Reservation> reservations = reservationService.retrieveAllReservations();

        assertEquals(mockReservations, reservations);
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveReservation() {
        Reservation mockReservation = new Reservation("1", new Date(), true, null);
        when(reservationRepository.findById("1")).thenReturn(Optional.of(mockReservation));

        Reservation reservation = reservationService.retrieveReservation("1");

        assertEquals(mockReservation, reservation);
        verify(reservationRepository, times(1)).findById("1");
    }

    @Test
    void testAddReservation() {
        Reservation reservationToAdd = new Reservation("1", new Date(), true, null);
        when(reservationRepository.save(reservationToAdd)).thenReturn(reservationToAdd);

        Reservation savedReservation = reservationService.addReservation(reservationToAdd);

        assertEquals(reservationToAdd, savedReservation);
        verify(reservationRepository, times(1)).save(reservationToAdd);
    }

    @Test
    void testModifyReservation() {
        Reservation reservationToModify = new Reservation("1", new Date(), false, null);
        when(reservationRepository.save(reservationToModify)).thenReturn(reservationToModify);

        Reservation modifiedReservation = reservationService.modifyReservation(reservationToModify);

        assertEquals(reservationToModify, modifiedReservation);
        verify(reservationRepository, times(1)).save(reservationToModify);
    }

    @Test
    void testRemoveReservation() {
        String reservationId = "1";

        reservationService.removeReservation(reservationId);

        verify(reservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    void testTrouverResSelonDateEtStatus() {
        Date date = new Date();
        boolean status = true;
        List<Reservation> mockReservations = List.of(new Reservation("1", date, status, null));
        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, status)).thenReturn(mockReservations);

        List<Reservation> reservations = reservationService.trouverResSelonDateEtStatus(date, status);

        assertEquals(mockReservations, reservations);
        verify(reservationRepository, times(1)).findAllByAnneeUniversitaireBeforeAndEstValide(date, status);
    }
}
