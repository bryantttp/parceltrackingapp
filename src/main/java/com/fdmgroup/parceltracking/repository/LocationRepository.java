/**
 * 
 * LocationRepository.java
 * - This file represents the DAO of the Location entity, which helps sync data 
 * of different Location entities with the database, using the EntityManagerFactory
 * object
 * 
 * @author Bryant Pang
 * @version 0.0.1
 * @since 22/03/24
 * 
 */
package com.fdmgroup.parceltracking.repository;

import com.fdmgroup.parceltracking.model.Location;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class LocationRepository {
	
	private EntityManagerFactory emf;
	
	/**
	 * 
	 * Custom constructor of LocationRepository class
	 * 
	 * @param emf EntityManagerFactory object to help carry out tasks to sync data
	 * with the database
	 */
	public LocationRepository(EntityManagerFactory emf) {
		this.setEmf(emf);
	}
	
	/**
	 * 
	 * Generic getter method that returns the EntityManagerFactory object
	 * of the LocationRepository class
	 * 
	 * @return EntityManagerFactory object that helps to sync Location data with
	 * the database
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	/**
	 * 
	 * Generic setter method that sets the EntityManagerFactory object
	 * of the LocationRepository class
	 * 
	 * @param emf EntityManagerFactory object of LocationRepository class
	 */
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	/**
	 * 
	 * This method helps sync data from Location object with the database
	 * 
	 * @param location Location object that would have their data synced with the 
	 * database
	 */
	public void persist(Location location) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(location);
		et.commit();
		em.close();
	}
	
	/**
	 * 
	 * This method helps update the Location object's data and sync the 
	 * information with the database
 	 * 
	 * @param location Location object that would be used to update the current
	 * Location's details
	 */
	public void update(Location location) {
		EntityManager em = emf.createEntityManager();
		Location tempLocation = em.find(Location.class,location.getLocationId());
		if (tempLocation != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			tempLocation.updateDetails(location);
			et.commit();
		}
		em.close();
	}
	
	/**
	 * 
	 * This method helps to delete Location objects and remove their
	 * particulars from the database based on their id
	 * 
	 * @param id ID of the Location
	 */
	public void deleteById(int id) {
		EntityManager em = emf.createEntityManager();
		Location tempLocation = em.find(Location.class,id);
		if (tempLocation != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(tempLocation);
			et.commit();
		}
		em.close();
	}
}
