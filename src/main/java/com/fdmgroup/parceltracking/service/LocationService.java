package com.fdmgroup.parceltracking.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parceltracking.model.Location;
import com.fdmgroup.parceltracking.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepo;
	
	private static Logger logger = LogManager.getLogger(LocationService.class);
	
	public LocationService(LocationRepository locationRepo) {
		this.locationRepo = locationRepo;
	}

	public void persist(Location location) {
		Optional<Location> returnedLocation = locationRepo.findById(location.getLocationId());
		if(returnedLocation.isEmpty()) {
			locationRepo.save(location);
			logger.info("Location successfully created");
		}
		else {
			logger.warn("Location already exists!");
		}
	}
	
	public void update(Location location) {
		Optional<Location> returnedLocation = locationRepo.findById(location.getLocationId());
		
		if(returnedLocation.isEmpty()) {
			logger.warn("Location does not exist in database!");
		}
		else {
			locationRepo.save(location);
			logger.info("Location successfully updated");
		}
	}
	
	public void deleteById(int id) {
		Optional<Location> returnedLocation = locationRepo.findById(id);
		
		if(returnedLocation.isEmpty()) {
			logger.warn("Location does not exist in database!");
		}
		else {
			locationRepo.deleteById(id);
			logger.info("Location deleted from Database");
		}
	}
	
	public boolean locationInDatabase(String country, String city) {
		Optional<Location> returnedLocation = locationRepo.findByCityAndCountry(country,city);
		if (returnedLocation.isEmpty()) {
			logger.info("Location does not exist in database");
			return false;
		}
		else {
			logger.info("Location exists in database");
			return true;
		}
	}
	
	public Location findByLocation(String country, String city) {
		Optional<Location> returnedLocation = locationRepo.findByCityAndCountry(country,city);
		if (returnedLocation.isEmpty()) {
			logger.warn("Location does not exist in database");
			return null;
		}
		else {
			logger.info("Location retrieved");
			return returnedLocation.get();
		}
	}
}
