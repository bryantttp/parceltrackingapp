package com.fdmgroup.parceltracking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.parceltracking.model.Parcel;
import com.fdmgroup.parceltracking.repository.ParcelRepository;

@Service
public class ParcelService {
	
	@Autowired
	private ParcelRepository parcelRepo;
	
	public void persist(Parcel parcel) {
		Optional<Parcel> returnedParcel = parcelRepo.findById(parcel.getParcelId());
		if(returnedParcel.isEmpty()) {
			parcelRepo.save(parcel);
			System.out.println("Parcel successfully created");
		}
		else {
			System.out.println("Parcel already exists!");
		}
	}
	
	public void update(Parcel parcel) {
		Optional<Parcel> returnedParcel = parcelRepo.findById(parcel.getParcelId());
		
		if(returnedParcel.isEmpty()) {
			System.out.println("Parcel does not exist in database!");
		}
		else {
			parcelRepo.save(parcel);
			System.out.println("Parcel successfully updated");
		}
	}
	
	public void deleteById(long id) {
		Optional<Parcel> returnedParcel = parcelRepo.findById(id);
		
		if(returnedParcel.isEmpty()) {
			System.out.println("Parcel does not exist in database!");
		}
		else {
			parcelRepo.deleteById(id);
			System.out.println("Parcel deleted from Database");
		}
	}
	
	public Parcel findById(long id) {
		Optional<Parcel> returnedParcel = parcelRepo.findById(id);
	
		if(returnedParcel.isEmpty()) {
			return null;
		}
		else {
			return returnedParcel.get();
		}
	}
}
