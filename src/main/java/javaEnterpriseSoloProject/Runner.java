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
package javaEnterpriseSoloProject;

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

		locationRepository.persist(SG);
		locationRepository.persist(CN);
		
		statusRepository.persist(shipping);
		statusRepository.persist(reachedLocal);
		statusRepository.persist(outForDelivery);
		
		customerRepository.persist(customer1);
		customerRepository.persist(customer2);
		
		parcelRepository.persist(parcel1);
		parcelRepository.persist(parcel2);
		parcelRepository.persist(parcel3);
		
//		emf.close();
	}
}
