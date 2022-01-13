package com.challenge.room.occupancy.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomsUsageAndRevenue {
	
	private int economyGuestSum;
	
	private int premiumGuestSum;
	
	private String economyIncomeSum;
	
	private String premiumIncomeSum;
}
