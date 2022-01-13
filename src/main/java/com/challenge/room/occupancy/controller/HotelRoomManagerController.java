package com.challenge.room.occupancy.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.challenge.room.occupancy.dto.RoomsAvailable;
import com.challenge.room.occupancy.dto.RoomsUsageAndRevenue;
import com.challenge.room.occupancy.service.RoomOccupancyManagerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("")
public class HotelRoomManagerController {
	
	@GetMapping
	public String testDefault() {
		return "setUp is running";
	}
	@Autowired
	private RoomOccupancyManagerService roomOccupancyManagerService;
	
	@PostMapping("/")
	@Operation(summary = "CalculateAvailableRoomAndIncome")
	public ResponseEntity<RoomsUsageAndRevenue> checkAvailableRooms(@RequestBody RoomsAvailable roomsAvailable){
		
		RoomsUsageAndRevenue roomsUsageAndRevenue = roomOccupancyManagerService.calculateRoomUsage(roomsAvailable);
		return new ResponseEntity<RoomsUsageAndRevenue>(roomsUsageAndRevenue, HttpStatus.CREATED);
		
	}
}
