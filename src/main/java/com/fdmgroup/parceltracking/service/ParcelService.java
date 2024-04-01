package com.fdmgroup.parceltracking.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parceltracking.model.Parcel;
import com.fdmgroup.parceltracking.repository.ParcelRepository;

@Service
public class ParcelService {
	
	@Autowired
	private ParcelRepository parcelRepo;
	
	private static Logger logger = LogManager.getLogger(ParcelService.class);
	
	public ParcelService(ParcelRepository parcelRepo) {
		this.parcelRepo = parcelRepo;
	}

	public void persist(Parcel parcel) {
		Optional<Parcel> returnedParcel = parcelRepo.findById(parcel.getParcelId());
		if(returnedParcel.isEmpty()) {
			parcelRepo.save(parcel);
			logger.info("Parcel successfully created");
		}
		else {
			logger.warn("Parcel already exists!");
		}
	}
	
	public void update(Parcel parcel) {
		Optional<Parcel> returnedParcel = parcelRepo.findById(parcel.getParcelId());
		if(returnedParcel.isEmpty()) {
			logger.warn("Parcel does not exist in database!");
		}
		else {
			parcelRepo.save(parcel);
			logger.info("Parcel successfully updated");
		}
	}
	
	public void deleteById(long id) {
		Optional<Parcel> returnedParcel = parcelRepo.findById(id);
		
		if(returnedParcel.isEmpty()) {
			logger.warn("Parcel does not exist in database!");
		}
		else {
			parcelRepo.deleteById(id);
			logger.info("Parcel deleted from Database");
		}
	}
	
	public Parcel findById(long id) {
		Optional<Parcel> returnedParcel = parcelRepo.findById(id);
	
		if(returnedParcel.isEmpty()) {
			logger.warn("Parcel retrieved from Database");
			return null;
		}
		else {
			logger.info("Parcel retrieved from Database");
			return returnedParcel.get();
		}
	}
}
