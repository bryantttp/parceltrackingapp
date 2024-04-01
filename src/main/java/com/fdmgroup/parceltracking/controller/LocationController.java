package com.fdmgroup.parceltracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.parceltracking.model.Location;
import com.fdmgroup.parceltracking.service.LocationService;

@Controller
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/locations")
	public String locationPage() {
		return "locationcreation";
	}
	
	@GetMapping("/locations/success")
	public String locationCreationSuccessPage() {
		return "locationcreationsuccess";
	}
	
	@GetMapping("/locations/delete")
	public String locationDeletionPage() {
		return "locationdeletion";
	}

	@GetMapping("/locations/delete/success")
	public String locationDeletionSuccessPage() {
		return "locationdeletionsuccess";
	}
	
	@PostMapping("/locations")
	public String createLocation(@RequestParam("country") String country, @RequestParam("city") String city) {
		Location location = new Location(country,city);
		if (locationService.locationInDatabase(country,city)) {
			return "locations";
		}
		else {
			locationService.persist(location);
			return "redirect:/locations/success";
		}
	}
	
	@PostMapping("/locations/delete")
	public String deleteLocation(Model model,@RequestParam("country") String country, @RequestParam("city") String city) {
		Location tempLocation = locationService.findByLocation(country,city);
		model.addAttribute("tempLocation", tempLocation);
		if (locationService.locationInDatabase(country,city)) {
			locationService.deleteById(tempLocation.getLocationId());
			return "redirect:/locations/delete/success";
		}
		else {
			return "redirect:/locations/delete";
		}
	}
}
