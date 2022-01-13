package com.challenge.room.occupancy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomsAvailable {
	
	private int availablePremiumRoom;
	
	private int availableEconomyRoom;

}
