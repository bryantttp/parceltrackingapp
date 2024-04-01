package com.fdmgroup.parceltracking.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parceltracking.model.Status;
import com.fdmgroup.parceltracking.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepo;
	
	private static Logger logger = LogManager.getLogger(StatusService.class);
	
	public StatusService(StatusRepository statusRepo) {
		this.statusRepo = statusRepo;
	}

	public void persist(Status status) {
		Optional<Status> returnedStatus = statusRepo.findById(status.getStatusId());
		if(returnedStatus.isEmpty()) {
			statusRepo.save(status);
			logger.info("Status successfully created");
		}
		else {
			logger.warn("Status already exists!");
		}
	}
	
	public void update(Status status) {
		Optional<Status> returnedStatus = statusRepo.findById(status.getStatusId());
		
		if(returnedStatus.isEmpty()) {
			logger.warn("Status does not exist in database!");
		}
		else {
			statusRepo.save(status);
			logger.info("Status successfully updated");
		}
	}
	
	public void deleteById(int id) {
		Optional<Status> returnedStatus = statusRepo.findById(id);
		if(returnedStatus.isEmpty()) {
			logger.warn("Status does not exist in database!");
		}
		else {
			statusRepo.deleteById(id);
			logger.info("Status deleted from Database");
		}
	}
	
	public boolean statusInDatabase(String statusName) {
		Optional<Status> returnedStatus = statusRepo.findByStatus(statusName);
		if (returnedStatus.isEmpty()) {
			logger.info("Status does not exist in database");
			return false;
		}
		else {
			logger.info("Status exists in database");
			return true;
		}
	}
	
	public Status findByStatus(String statusName) {
		Optional<Status> returnedStatus = statusRepo.findByStatus(statusName);
		if (returnedStatus.isEmpty()) {
			logger.info("Status does not exist in database");
			return null;
		}
		else {
			logger.info("Status retrieved from Database");
			return returnedStatus.get();
		}
	}
}
