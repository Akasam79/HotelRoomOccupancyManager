package com.challenge.room.occupancy.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class Controller {
	
	@GetMapping
	public String testDefault() {
		return "setUp is running";
	}
}
