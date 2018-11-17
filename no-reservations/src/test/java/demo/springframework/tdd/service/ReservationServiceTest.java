package demo.springframework.tdd.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import demo.springframework.tdd.exception.ReservationNotFoundException;
import demo.springframework.tdd.model.Reservation;
import demo.springframework.tdd.repository.ReservationRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {
	ReservationService reservationService;

	@Mock
	ReservationRepository reservationRepository;

	@Before
	public void setUp() throws Exception {
		reservationService = new ReservationService(reservationRepository);
	}

	@Test
	public void getReservationDetails_returnsReservationInfo() {
		BDDMockito.given(reservationRepository.findByFirstName("Antknee"))
				.willReturn(new Reservation(Long.valueOf(1), "Antknee", "Boredayn"));

		Reservation aReservation = reservationService.getReservationDetails("Antknee");

		assertThat(aReservation.getFirstName()).isEqualTo("Antknee");
		assertThat(aReservation.getLastName()).isEqualTo("Boredayn");
	}
	
	@Test(expected = ReservationNotFoundException.class)
	public void getReservationDetails_whenNotFound() {
		BDDMockito.given(reservationRepository.findByFirstName("Billybob")).willReturn(null);
		Reservation aReservation = reservationService.getReservationDetails("Billybob");
	}
}