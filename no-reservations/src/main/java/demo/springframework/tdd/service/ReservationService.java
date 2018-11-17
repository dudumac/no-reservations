package demo.springframework.tdd.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import demo.springframework.tdd.exception.ReservationNotFoundException;
import demo.springframework.tdd.model.Reservation;
import demo.springframework.tdd.repository.ReservationRepository;

@Service
public class ReservationService {
	private ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	
	@Cacheable("reservation")
	public Reservation getReservationDetails(String name) {
		Reservation reservation = reservationRepository.findByFirstName(name);
		if (reservation == null) {
			throw new ReservationNotFoundException();
		}
		return reservation;
	}
}
