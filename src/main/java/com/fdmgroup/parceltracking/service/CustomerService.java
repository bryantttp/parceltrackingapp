package com.fdmgroup.parceltracking.service;


import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parceltracking.model.Customer;
import com.fdmgroup.parceltracking.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	
	private static Logger logger = LogManager.getLogger(CustomerService.class);
	
	public CustomerService(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	public void persist(Customer customer) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(customer.getUsername());
		if(returnedCustomer.isEmpty()) {
			customerRepo.save(customer);
			logger.info("Customer successfully registered");
		}
		else {
			logger.warn("Customer already exists");
		}
	}
	
	public boolean usernameInDatabase(String username) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		if (returnedCustomer.isEmpty()) {
			logger.info("Username does not exist in database");
			return false;
		}
		else {
			logger.info("Username exists in database");
			return true;
		}
	}
	
	public boolean passwordChecker(String username, String password) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		if (returnedCustomer.isEmpty()) {
			logger.warn("Username does not exist in database");
			return false;
		}
		else if (returnedCustomer.get().getPassword().equals(password)) {
			logger.info("Username and Password validated");
			return true;
		}
		else {
			logger.warn("Username and Password incorrect");
			return false;
		}
	}
	
	public void findParcelsByUsername(String username) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		
		if(returnedCustomer.isEmpty()) {
			logger.warn("Could not find Customer's details");
		}
		else {
			returnedCustomer.get().getParcelDetails();
			logger.info("Returning customer's parcel details");
		}
	}
	
	public Customer findCustomerById(long customerId) {
		Optional<Customer> returnedCustomer = customerRepo.findById(customerId);
		if (returnedCustomer.isEmpty()) {
			logger.warn("Could not find Customer in Database");
			return null;
		}
		else {
			logger.info("Returning customer's details");
			return returnedCustomer.get();
		}
	}
	
	public Customer findCustomerByUsername(String username) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		if (returnedCustomer.isEmpty()) {
			logger.warn("Could not find Customer in Database");
			return null;
		}
		else {
			logger.info("Returning customer's details");
			return returnedCustomer.get();
		}
	}
	
	public void update(Customer customer) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(customer.getUsername());
		
		if(returnedCustomer.isEmpty()) {
			logger.warn("Customer does not exist in database");
		}
		else {
			customerRepo.save(customer);
			logger.info("Customer successfully updated");
		}
	}
	
	public void deleteById(long id) {
		Optional<Customer> returnedCustomer = customerRepo.findById(id);
		
		if(returnedCustomer.isEmpty()) {
			logger.warn("Customer does not exist in database");
		}
		else {
			customerRepo.deleteById(id);
			logger.info("Customer deleted from Database");
		}
	}
}
