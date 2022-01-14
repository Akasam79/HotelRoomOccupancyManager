package com.challenge.room.occupancy;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.challenge.room.occupancy.dto.RoomsAvailable;
import com.challenge.room.occupancy.dto.RoomsUsageAndRevenue;
import com.challenge.room.occupancy.service.RoomOccupancyManagerService;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class HotelRoomOccupancyServiceTest {
	
	@Autowired
	private RoomOccupancyManagerService roomOccupancyManagerService;

	@Test
	@DisplayName("Test with available rooms - Premium 3 and Economy 3")
	void test_premium_3_economy_3_shouldReturn_premium_3_economy_3() {
		
		// given
		RoomsAvailable roomsAvailable = RoomsAvailable.builder()
				.availableEconomyRoom(3)
				.availablePremiumRoom(3)
				.build();
		
		/// when
		RoomsUsageAndRevenue usageAndRevenue = roomOccupancyManagerService.calculateRoomUsage(roomsAvailable);

		//then
		then(usageAndRevenue.getEconomyGuestSum()).isEqualTo(3);
		then(usageAndRevenue.getEconomyIncomeSum()).isEqualTo("EUR 167.99");
		then(usageAndRevenue.getPremiumGuestSum()).isEqualTo(3);
		then(usageAndRevenue.getPremiumIncomeSum()).isEqualTo("EUR 738.0");
	}
	
	@Test
	@DisplayName("Test with available rooms - Premium 7 and Economy 5")
	void test_premium_7_economy_5_shouldReturn_premium_6_economy_4() {
		
		// given
		RoomsAvailable roomsAvailable = RoomsAvailable.builder()
				.availableEconomyRoom(5)
				.availablePremiumRoom(7)
				.build();
		
		/// when
		RoomsUsageAndRevenue usageAndRevenue = roomOccupancyManagerService.calculateRoomUsage(roomsAvailable);

		//then
		then(usageAndRevenue.getEconomyGuestSum()).isEqualTo(4);
		then(usageAndRevenue.getEconomyIncomeSum()).isEqualTo("EUR 189.99");
		then(usageAndRevenue.getPremiumGuestSum()).isEqualTo(6);
		then(usageAndRevenue.getPremiumIncomeSum()).isEqualTo("EUR 1054.0");
	}
	
	@Test
	@DisplayName("Test with available rooms - Premium 2 and Economy 7")
	void test_premium_2_economy_7_shouldReturn_premium_2_economy_4() {
		
		// given
		RoomsAvailable roomsAvailable = RoomsAvailable.builder()
				.availableEconomyRoom(7)
				.availablePremiumRoom(2)
				.build();
		
		/// when
		RoomsUsageAndRevenue usageAndRevenue = roomOccupancyManagerService.calculateRoomUsage(roomsAvailable);

		//then
		then(usageAndRevenue.getEconomyGuestSum()).isEqualTo(4);
		then(usageAndRevenue.getEconomyIncomeSum()).isEqualTo("EUR 189.99");
		then(usageAndRevenue.getPremiumGuestSum()).isEqualTo(2);
		then(usageAndRevenue.getPremiumIncomeSum()).isEqualTo("EUR 583.0");
	}
	
	
	@Test
	@DisplayName("Test with available rooms - Premium 7 and Economy 1")
	void test_premium_7_economy_1_shouldReturn_premium_7_economy_1() {
		
		// given
		RoomsAvailable roomsAvailable = RoomsAvailable.builder()
				.availableEconomyRoom(1)
				.availablePremiumRoom(7)
				.build();
		
		/// when
		RoomsUsageAndRevenue usageAndRevenue = roomOccupancyManagerService.calculateRoomUsage(roomsAvailable);

		//then
		then(usageAndRevenue.getEconomyGuestSum()).isEqualTo(1);
		then(usageAndRevenue.getEconomyIncomeSum()).isEqualTo("EUR 45.0");
		then(usageAndRevenue.getPremiumGuestSum()).isEqualTo(7);
		then(usageAndRevenue.getPremiumIncomeSum()).isNotEqualTo("EUR 1153.0");
	}

}
