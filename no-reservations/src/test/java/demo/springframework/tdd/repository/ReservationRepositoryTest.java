package demo.springframework.tdd.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import demo.springframework.tdd.model.Reservation;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepositoryTest {
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Test
	public void getReservation_returnReservationDetails() {
		Reservation savedReservation = entityManager.persistAndFlush(new Reservation("Antknee","Boredayn"));
		
		Reservation aReservation = reservationRepository.findByFirstName("Antknee");
		
		Assertions.assertThat(aReservation.getFirstName()).isEqualTo(savedReservation.getFirstName());
		Assertions.assertThat(aReservation.getLastName()).isEqualTo(savedReservation.getLastName());
	}
}