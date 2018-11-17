package demo.springframework.tdd.repository;

import org.springframework.data.repository.CrudRepository;

import demo.springframework.tdd.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	Reservation findByFirstName(String name);
}