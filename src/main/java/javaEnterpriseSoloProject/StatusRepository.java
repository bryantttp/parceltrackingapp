/**
 * 
 * StatusRepository.java
 * - This file represents the DAO of the Status entity, which helps sync data 
 * of different Status entities with the database, using the EntityManagerFactory
 * object
 * 
 * @author Bryant Pang
 * @version 0.0.1
 * @since 22/03/24
 * 
 */
package javaEnterpriseSoloProject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class StatusRepository {
	
	private EntityManagerFactory emf;
	
	/**
	 * 
	 * Custom constructor for StatusRepository class
	 * 
	 * @param emf
	 */
	public StatusRepository(EntityManagerFactory emf) {
		this.setEmf(emf);
	}
	
	/**
	 * 
	 * Generic getter method that returns the EntityManagerFactory object
	 * of the StatusRepository class
	 * 
	 * @return EntityManagerFactory object that helps to sync Status data with
	 * the database
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	/**
	 * 
	 * Generic setter method that returns the EntityManagerFactory object
	 * of the StatusRepository class
	 * 
	 * @param emf EntityManagerFactory object of the StatusRepository class
	 */
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	/**
	 * 
	 * This method helps sync data from Status object with the database
	 * 
	 * @param status Status that would have their data synced with the 
	 * database
	 */
	public void persist(Status status) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(status);
		et.commit();
		em.close();
	}
	
	/**
	 * 
	 * This method helps update the details of the Status object and sync the 
	 * information with the database
	 * 
	 * @param status Status object that would be used to update the current
	 * Status' details
	 */
	public void update(Status status) {
		EntityManager em = emf.createEntityManager();
		Status tempStatus = em.find(Status.class,status.getStatusId());
		if (tempStatus != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			tempStatus.updateDetails(status);
			et.commit();
		}
		em.close();
	}
	
	/**
	 * 
	 * This method helps delete Status objects and remove their
	 * particulars from the database based on their id
	 * 
	 * @param id ID of the Status object
	 */
	public void deleteById(int id) {
		EntityManager em = emf.createEntityManager();
		Status tempStatus = em.find(Status.class,id);
		if (tempStatus != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(tempStatus);
			et.commit();
		}
		em.close();
	}
}
