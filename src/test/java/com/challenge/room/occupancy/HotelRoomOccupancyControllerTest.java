package com.challenge.room.occupancy;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge.room.occupancy.dto.RoomsAvailable;
import com.challenge.room.occupancy.dto.RoomsUsageAndRevenue;
import com.challenge.room.occupancy.service.RoomOccupancyManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class HotelRoomOccupancyControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RoomOccupancyManagerService roomOccupancyManagerService;

	@Test
	@DisplayName("Check possible room Usage and Income")
	void getPossibleRoomUsage_shouldReturnAvailableRoomsAndIncome() throws Exception {

		// given
		RoomsAvailable roomsAvailable = RoomsAvailable.builder()
				.availableEconomyRoom(3)
				.availablePremiumRoom(3)
				.build();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(roomsAvailable);	
		
		// when
		given(roomOccupancyManagerService.calculateRoomUsage(ArgumentMatchers.any())).willReturn(RoomsUsageAndRevenue.builder()
				.economyGuestSum(3)
				.premiumGuestSum(3)
				.economyIncomeSum("EUR 167.99")
				.premiumIncomeSum("EUR 738.0")
				.build());

		//then
		mockMvc.perform(post("/")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("economyGuestSum").value(3))
				.andExpect(jsonPath("premiumGuestSum").value(3))
				.andExpect(jsonPath("economyIncomeSum").value("EUR 167.99"))
				.andExpect(jsonPath("premiumIncomeSum").value("EUR 738.0"));
	}

}
