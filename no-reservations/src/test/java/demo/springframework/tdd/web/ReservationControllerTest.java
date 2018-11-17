package demo.springframework.tdd.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import demo.springframework.tdd.exception.ReservationNotFoundException;
import demo.springframework.tdd.model.Reservation;
import demo.springframework.tdd.service.ReservationService;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ReservationService reservationService;

	@Test
	public void getReservation_shouldReturnReservationInfo() throws Exception, ReservationNotFoundException {
		BDDMockito.given(reservationService.getReservationDetails(ArgumentMatchers.anyString()))
				.willReturn(new Reservation(Long.valueOf(1), "antknee", "boredayn"));

		mockMvc.perform(MockMvcRequestBuilders.get("/reservation/antknee"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("firstName").value("antknee"))
				.andExpect(MockMvcResultMatchers.jsonPath("lastName").value("boredayn"));
	}
	
	// Ammend this to catch the expected exception
	@Test
	public void getReservation_NotFound() throws Exception, ReservationNotFoundException {
		BDDMockito.given(reservationService.getReservationDetails(ArgumentMatchers.anyString()))
					.willThrow(new ReservationNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/reservation/boredayn"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}