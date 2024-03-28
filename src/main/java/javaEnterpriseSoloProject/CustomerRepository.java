/**
 * 
 * CustomerRepository.java
 * - This file represents the DAO of the Customer entity, which helps meet the 
 * functional requirements of the Customer with the help of the 
 * EntityManagerFactory
 * 
 * @author Bryant Pang
 * @version 0.0.1
 * @since 22/03/24
 * 
 */
package javaEnterpriseSoloProject;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CustomerRepository {
	
	private EntityManagerFactory emf;
	
	/**
	 * 
	 * Custom constructor of CustomerRepository class
	 * 
	 * @param emf EntityManagerFactory object to help carry out tasks to sync data
	 * with the database
	 */
	public CustomerRepository(EntityManagerFactory emf) {
		this.setEmf(emf);
	}
	
	/**
	 * 
	 * Generic getter method that returns the EntityManagerFactory object
	 * of the CustomerRepository class
	 * 
	 * @return EntityManagerFactory object that helps to sync Customer data with
	 * the database
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}
	
	/**
	 * 
	 * Generic setter method that sets the EntityManagerFactory object
	 * of the CustomerRepository class
	 * 
	 * @param emf EntityManagerFactory object of CustomerRepository class
	 */
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	/**
	 * 
	 * This method helps sync data from Customer with the database
	 * 
	 * @param customer Customer that would have their data synced with the 
	 * database
	 */
	public void persist(Customer customer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(customer);
		et.commit();
		em.close();
	}
	
	/**
	 * 
	 * This method helps Customers retrieve detailed information of their parcels
	 * based on the username of the Customer
	 * 
	 * @param username Username of the Customer
	 */
	public void findParcelsByUsername(String username) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findByUsername", Customer.class);
		List<Customer> matchedCustomers = query.setParameter("customerUsername", username).getResultList();
		matchedCustomers.forEach(m -> m.getParcelDetails());
		em.close();
	}
	
	/**
	 * 
	 * This method helps Customers update their particulars and sync the 
	 * information with the database
 	 * 
	 * @param customer Customer object that would be used to update the current
	 * Customer's details
	 */
	public void update(Customer customer) {
		EntityManager em = emf.createEntityManager();
		Customer tempCustomer = em.find(Customer.class,customer.getId());
		if (tempCustomer != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			tempCustomer.updateDetails(customer);
			et.commit();
		}
		em.close();
	}
	
	/**
	 * 
	 * This method helps to Customers delete their accounts and remove their
	 * particulars from the database based on their id
	 * 
	 * @param id ID of the Customer
	 */
	public void deleteById(long id) {
		EntityManager em = emf.createEntityManager();
		Customer tempCustomer = em.find(Customer.class,id);
		if (tempCustomer != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(tempCustomer);
			et.commit();
		}
		em.close();
	}
}
