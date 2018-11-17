package demo.springframework.tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NoReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoReservationsApplication.class, args);
	}
}
