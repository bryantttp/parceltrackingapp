/**
 * 
 * Runner.java
 * - This file simulates the Java code which helps sync Customer, Parcel,
 * Location and Status entities to the database and carry out any tasks such as
 * retrieving information on parcels and updating the location and status of 
 * parcels
 * 
 * @author Bryant Pang
 * @version 0.0.1
 * @since 22/03/24
 * 
 */
package com.fdmgroup.parceltracking;

import com.fdmgroup.parceltracking.model.Customer;
import com.fdmgroup.parceltracking.model.Location;
import com.fdmgroup.parceltracking.model.Parcel;
import com.fdmgroup.parceltracking.model.Status;
import com.fdmgroup.parceltracking.repository.CustomerRepository;
import com.fdmgroup.parceltracking.repository.LocationRepository;
import com.fdmgroup.parceltracking.repository.ParcelRepository;
import com.fdmgroup.parceltracking.repository.StatusRepository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Runner {
	/**
	 * 
	 * Main method of the Runner class to execute Java code
	 * 
	 * @param args
	 */
	public static void main (String[] args) {
		Location SG = new Location("Singapore","Singapore");
		Location CN = new Location("China","Shenzhen");
		
		Status shipping = new Status("Shipping From Origin");
		Status reachedLocal = new Status("Reaching Local Facility");
		Status outForDelivery = new Status("Out for delivery");
		Status error = new Status("Error");
				
		Customer customer1 = new Customer("BLK 64 YUNG KUANG ROAD, #01-113","john.doe","johndoe123","John","Doe");
		Customer customer2 = new Customer("Ion Orchard, 2 Orchard Turn,","chanel.ion","chanelion123","Chanel","Store");
		
		Parcel parcel1 = new Parcel(customer1, CN, shipping);
		Parcel parcel2 = new Parcel(customer2, SG, reachedLocal);
		Parcel parcel3 = new Parcel(customer1, SG, outForDelivery);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
		CustomerRepository customerRepository = new CustomerRepository(emf);
		ParcelRepository parcelRepository = new ParcelRepository(emf);
		LocationRepository locationRepository = new LocationRepository(emf);
		StatusRepository statusRepository = new StatusRepository(emf);
		
		// Create all entities
		locationRepository.persist(SG);
		locationRepository.persist(CN);
		
		statusRepository.persist(shipping);
		statusRepository.persist(reachedLocal);
		statusRepository.persist(outForDelivery);
//		statusRepository.persist(error);
		
		customerRepository.persist(customer1);
		customerRepository.persist(customer2);
		
		parcelRepository.persist(parcel1);
		parcelRepository.persist(parcel2);
		parcelRepository.persist(parcel3);
		
		// Update Parcel 1 details
//		parcel1.setLocation(SG);
//		parcel1.setStatus(null);
//		parcelRepository.update(parcel1);
		
		// Read Parcel 2 details
//		customerRepository.findParcelsByUsername(customer1.getUsername());
		
		// Delete Status entity error
//		statusRepository.deleteById(reachedLocal.getStatusId());
		
//		customerRepository.deleteById(customer1.getId());
		
//		emf.close();
	}
}
