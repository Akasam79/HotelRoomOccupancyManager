package com.challenge.room.occupancy;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
	
	@ParameterizedTest
	@MethodSource("valueInputs")
	@DisplayName("Test with available rooms")
	void test_with_Arguments(int availableEconomy, int availablePremium) {
		
		RoomsAvailable roomsAvailable = RoomsAvailable.builder()
				.availableEconomyRoom(availableEconomy)
				.availablePremiumRoom(availablePremium)
				.build();
		
		RoomsUsageAndRevenue usageAndRevenue = roomOccupancyManagerService.calculateRoomUsage(roomsAvailable);

		if(availableEconomy == 3) {
			then (usageAndRevenue.getEconomyGuestSum()).isEqualTo(3);
			then(usageAndRevenue.getEconomyIncomeSum()).isEqualTo("EUR 167.99");
			then(usageAndRevenue.getPremiumGuestSum()).isEqualTo(3);
			then(usageAndRevenue.getPremiumIncomeSum()).isEqualTo("EUR 738.0");
		}else if(availableEconomy == 5) {
			then(usageAndRevenue.getEconomyGuestSum()).isEqualTo(4);
			then(usageAndRevenue.getEconomyIncomeSum()).isEqualTo("EUR 189.99");			
			then(usageAndRevenue.getPremiumGuestSum()).isEqualTo(6);
			then(usageAndRevenue.getPremiumIncomeSum()).isEqualTo("EUR 1054.0");
		}else if(availableEconomy == 7) {
			then(usageAndRevenue.getEconomyGuestSum()).isEqualTo(4);
			then(usageAndRevenue.getEconomyIncomeSum()).isEqualTo("EUR 189.99");
			then(usageAndRevenue.getPremiumGuestSum()).isEqualTo(2);
			then(usageAndRevenue.getPremiumIncomeSum()).isEqualTo("EUR 583.0");
		}else {
			then(usageAndRevenue.getEconomyGuestSum()).isEqualTo(1);
			then(usageAndRevenue.getEconomyIncomeSum()).isEqualTo("EUR 45.0");
			then(usageAndRevenue.getPremiumGuestSum()).isEqualTo(7);
			then(usageAndRevenue.getPremiumIncomeSum()).isNotEqualTo("EUR 1153.0");
		}
		
	}
	
	private static Stream<Arguments> valueInputs(){
		return Stream.of(
				Arguments.of(3, 3),
				Arguments.of(5, 7),
				Arguments.of(7, 2),
				Arguments.of(1, 7)
				);
	}


}
