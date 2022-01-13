package com.challenge.room.occupancy.service;
import java.util.Arrays;

import org.slf4j.*;
import org.springframework.stereotype.*;

import com.challenge.room.occupancy.dto.RoomsAvailable;
import com.challenge.room.occupancy.dto.RoomsUsageAndRevenue;
import com.challenge.room.occupancy.util.Constants;

@Service
public class RoomOccupancyManagerService {
	
	Logger logger = LoggerFactory.getLogger(RoomOccupancyManagerService.class);
	
	private double[] potentialGuests = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};
	
	public RoomsUsageAndRevenue calculateRoomUsage(RoomsAvailable roomsAvailable) {
		
		Arrays.sort(potentialGuests);
		
		int premiumRoomsUsed = 0;
		int economyRoomsUsed = 0;
		double premiumIncomeSum = 0;
		double economyIncomeSum = 0;
		
		for (int i = potentialGuests.length-1; i >= 0; i--) {
			double bidPrice = potentialGuests[i];
			
			if(premiumRoomsUsed < roomsAvailable.getAvailablePremiumRoom() 
					&& bidPrice >= Constants.PREMIUM_MINIMUM_AMOUNT) {
				
				premiumRoomsUsed++;
				
				premiumIncomeSum += potentialGuests[i];
				logger.info("value : "+ bidPrice);
				logger.info("value : "+ (i+1));
				
			} else {
				if(premiumRoomsUsed < roomsAvailable.getAvailablePremiumRoom() && 
						bidPrice < Constants.PREMIUM_MINIMUM_AMOUNT && 
						((i+1) > roomsAvailable.getAvailableEconomyRoom())) {
					premiumRoomsUsed++;
					premiumIncomeSum += potentialGuests[i];
					logger.info("valu : " + bidPrice);
					logger.info("value : " +(i + 1));
					
				}else if(economyRoomsUsed < roomsAvailable.getAvailableEconomyRoom() 
						&& bidPrice < Constants.PREMIUM_MINIMUM_AMOUNT) {
					
					economyRoomsUsed++;
					economyIncomeSum += potentialGuests[i];
				}
			}
			
		}
		return RoomsUsageAndRevenue.builder()
				.economyGuestSum(economyRoomsUsed).economyIncomeSum(Constants.CURRENCY + economyIncomeSum)
				.premiumGuestSum(premiumRoomsUsed).premiumIncomeSum(Constants.CURRENCY + premiumIncomeSum).build();
	}
	
	

}
