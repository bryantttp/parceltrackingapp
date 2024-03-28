/**
 * 
 * ParcelRepository.java
 * - This file represents the DAO of the Parcel entity, which helps sync data 
 * of different Parcel entities with the database, using the EntityManagerFactory
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

public class ParcelRepository {
	
	private EntityManagerFactory emf;
	
	/**
	 * 
	 * Custom constructor of ParcelRepository class
	 * 
	 * @param emf EntityManagerFactory object to help carry out tasks to sync data
	 * with the database
	 */
	public ParcelRepository(EntityManagerFactory emf) {
		this.setEmf(emf);
	}
	
	/**
	 * 
	 * Generic getter method that returns the EntityManagerFactory object
	 * of the ParcelRepository class
	 * 
	 * @return EntityManagerFactory object that helps to sync Parcel data with
	 * the database
	 * 
	 * @return
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	/**
	 * 
	 * Generic setter method that sets the EntityManagerFactory object
	 * of the ParcelRepository class
	 * 
	 * @param emf EntityManagerFactory object of ParcelRepository class
	 */
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	/**
	 * 
	 * This method helps sync data from Parcel with the database
	 * 
	 * @param parcel Parcel that would have their data synced with the 
	 * database
	 */
	public void persist(Parcel parcel) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(parcel);
		et.commit();
		em.close();
	}
	
	/**
	 * 
	 * This method helps update the particulars of the Parcel object and sync the 
	 * information with the database
 	 * 
	 * @param parcel Parcel object that would be used to update the current
	 * Parcel's details
	 */
	public void update(Parcel parcel) {
		EntityManager em = emf.createEntityManager();
		Parcel tempParcel = em.find(Parcel.class,parcel.getParcelId());
		if (tempParcel != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			tempParcel.updateDetails(parcel);
			et.commit();
		}
		em.close();
	}
	
	/**
	 * 
	 * This method helps to delete Parcel objects and remove their
	 * particulars from the database based on their id
	 * 
	 * @param id ID of the Parcel
	 */
	public void deleteById(long id) {
		EntityManager em = emf.createEntityManager();
		Parcel tempParcel = em.find(Parcel.class,id);
		if (tempParcel != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(tempParcel);
			et.commit();
		}
		em.close();
	}
}
