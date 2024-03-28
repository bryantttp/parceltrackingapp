package com.fdmgroup.parceltracking.service;


import java.util.Optional;

import com.fdmgroup.parceltracking.model.Customer;
import com.fdmgroup.parceltracking.repository.CustomerRepository;

public class CustomerService {
	
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
	
	public void findParcelsByUsername(String username) {
		Optional<Customer> returnedCustomer = customerRepo.findByUsername(username);
		
		if(returnedCustomer.isEmpty()) {
			System.out.println("Error! Could not find Customer's details!");
		}
		else {
			returnedCustomer.ifPresent(Customer::getParcelDetails);
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
