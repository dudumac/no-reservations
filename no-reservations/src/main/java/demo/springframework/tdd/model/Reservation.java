package demo.springframework.tdd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	
	public Reservation(Long id, String name, String familyName) {
		this.id = id;
		this.firstName = name;
		this.lastName = familyName;
	}

	public Reservation(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
