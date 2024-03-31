package com.fdmgroup.parceltracking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parceltracking.model.Status;
import com.fdmgroup.parceltracking.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepo;
	
	public void persist(Status status) {
		Optional<Status> returnedStatus = statusRepo.findById(status.getStatusId());
		if(returnedStatus.isEmpty()) {
			statusRepo.save(status);
			System.out.println("Status successfully created");
		}
		else {
			System.out.println("Status already exists!");
		}
	}
	
	public void update(Status status) {
		Optional<Status> returnedStatus = statusRepo.findById(status.getStatusId());
		
		if(returnedStatus.isEmpty()) {
			System.out.println("Status does not exist in database!");
		}
		else {
			statusRepo.save(status);
			System.out.println("Status successfully updated");
		}
	}
	
	public void deleteById(int id) {
		Optional<Status> returnedStatus = statusRepo.findById(id);
		
		if(returnedStatus.isEmpty()) {
			System.out.println("Status does not exist in database!");
		}
		else {
			statusRepo.deleteById(id);
			System.out.println("Status deleted from Database");
		}
	}
	
	public boolean statusInDatabase(String statusName) {
		Optional<Status> returnedStatus = statusRepo.findByStatus(statusName);
		if (returnedStatus.isEmpty()) {
			System.out.println("Location does not exist in database");
			return false;
		}
		else {
			System.out.println("Location exists in database");
			return true;
		}
	}
	
	public Status findByStatus(String statusName) {
		Optional<Status> returnedStatus = statusRepo.findByStatus(statusName);
		if (returnedStatus.isEmpty()) {
			return null;
		}
		else {
			return returnedStatus.get();
		}
	}
}
