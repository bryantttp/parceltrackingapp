package javaEnterpriseSoloProject;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;


public class Runner {
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
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		em.persist(SG);
		em.persist(CN);
		
		em.persist(shipping);
		em.persist(reachedLocal);
		em.persist(outForDelivery);
		
		em.persist(parcel1);
		em.persist(parcel2);
		em.persist(parcel3);
		
		customerRepository.persist(customer1);
		customerRepository.persist(customer2);
		
		et.commit();
		

		
//		TypedQuery<Customer> findCustomerByCustomerFirstNameQuery = em.createNamedQuery("Customer.findByUsername", Customer.class);
//		List<Customer> matchedCustomers = findCustomerByCustomerFirstNameQuery.setParameter("customerUsername", "chanel.ion").getResultList();
//		matchedCustomers.forEach(m -> m.getParcels());
	}
}
