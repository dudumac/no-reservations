package demo.springframework.tdd;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import demo.springframework.tdd.model.Reservation;
import demo.springframework.tdd.repository.ReservationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NoReservationsApplicationTests {
	@Autowired
	TestRestTemplate testRestTemplate;
	
	@Test
	public void getReservation_shouldReturnReservationDetails() {
		// Given
		
		// When
		ResponseEntity<Reservation> response = testRestTemplate.getForEntity("/reservation/{name}", Reservation.class, "Antknee");
		
		// Then
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getLastName()).isEqualTo("Boredayn");
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}
}

// Bootstrap EndToEnd Test Data
@Component
class SampleDataCLR implements CommandLineRunner {
	private ReservationRepository reservationRepository;
	
	public SampleDataCLR(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("@@@@@@@@@@@@@@ Entering SampleDataCLR : run");
		reservationRepository.save(new Reservation("Antknee","Boredayn"));
		reservationRepository.save(new Reservation("Yerman","There"));
		reservationRepository.findAll().forEach(System.out :: println);
		System.err.println("@@@@@@@@@@@@@@ Leaving SampleDataCLR : run");
	}
}
