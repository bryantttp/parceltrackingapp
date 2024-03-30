package com.fdmgroup.parceltracking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parceltracking.model.Customer;
import com.fdmgroup.parceltracking.model.Location;
import com.fdmgroup.parceltracking.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepo;
	
	public void persist(Location location) {
		Optional<Location> returnedLocation = locationRepo.findById(location.getLocationId());
		if(returnedLocation.isEmpty()) {
			locationRepo.save(location);
			System.out.println("Location successfully created");
		}
		else {
			System.out.println("Location already exists!");
		}
	}
	
	public void update(Location location) {
		Optional<Location> returnedLocation = locationRepo.findById(location.getLocationId());
		
		if(returnedLocation.isEmpty()) {
			System.out.println("Location does not exist in database!");
		}
		else {
			locationRepo.save(location);
			System.out.println("Location successfully updated");
		}
	}
	
	public void deleteById(int id) {
		Optional<Location> returnedLocation = locationRepo.findById(id);
		
		if(returnedLocation.isEmpty()) {
			System.out.println("Location does not exist in database!");
		}
		else {
			locationRepo.deleteById(id);
			System.out.println("Location deleted from Database");
		}
	}
	
	public boolean locationInDatabase(String country, String city) {
		Optional<Location> returnedLocation = locationRepo.findByCityAndCountry(country,city);
		if (returnedLocation.isEmpty()) {
			System.out.println("Location does not exist in database");
			return false;
		}
		else {
			System.out.println("Location exists in database");
			return true;
		}
	}
	
	public Location findByLocation(String country, String city) {
		Optional<Location> returnedLocation = locationRepo.findByCityAndCountry(country,city);
		if (returnedLocation.isEmpty()) {
			return null;
		}
		else {
			return returnedLocation.get();
		}
	}
}
