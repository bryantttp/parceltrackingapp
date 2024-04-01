package com.fdmgroup.parceltracking.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parceltracking.model.Customer;
import com.fdmgroup.parceltracking.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	
	public CustomerService(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	public void persist(Customer customer) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(customer.getUsername());
		
		if(returnedCustomer.isEmpty()) {
			customerRepo.save(customer);
			System.out.println("Customer successfully registered");
		}
		else {
			System.out.println("Customer already exists!");
		}
	}
	
	public boolean usernameInDatabase(String username) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		if (returnedCustomer.isEmpty()) {
			System.out.println("Username does not exist in database");
			return false;
		}
		else {
			System.out.println("Username exists in database");
			return true;
		}
	}
	
	public boolean passwordChecker(String username, String password) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		if (returnedCustomer.isEmpty()) {
			System.out.println("Username does not exist in database");
			return false;
		}
		else if (returnedCustomer.get().getPassword().equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void findParcelsByUsername(String username) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		
		if(returnedCustomer.isEmpty()) {
			System.out.println("Error! Could not find Customer's details!");
		}
		else {
			returnedCustomer.get().getParcelDetails();
		}
	}
	
	public Customer findCustomerById(long customerId) {
		Optional<Customer> returnedCustomer = customerRepo.findById(customerId);
		if (returnedCustomer.isEmpty()) {
			return null;
		}
		else {
			return returnedCustomer.get();
		}
	}
	
	public Customer findCustomerByUsername(String username) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		if (returnedCustomer.isEmpty()) {
			return null;
		}
		else {
			return returnedCustomer.get();
		}
	}
	
	public void update(Customer customer) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(customer.getUsername());
		
		if(returnedCustomer.isEmpty()) {
			System.out.println("Customer does not exist in database!");
		}
		else {
			customerRepo.save(customer);
			System.out.println("Customer successfully updated");
		}
	}
	
	public void deleteById(long id) {
		Optional<Customer> returnedCustomer = customerRepo.findById(id);
		
		if(returnedCustomer.isEmpty()) {
			System.out.println("Customer does not exist in database!");
		}
		else {
			customerRepo.deleteById(id);
			System.out.println("Customer deleted from Database");
		}
	}
}
