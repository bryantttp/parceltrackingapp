package javaEnterpriseSoloProject;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CustomerRepository {
	private EntityManagerFactory emf;

	public CustomerRepository(EntityManagerFactory emf) {
		this.setEmf(emf);
	}
	public EntityManagerFactory getEmf() {
		return emf;
	}
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void persist(Customer customer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(customer);
		et.commit();
		em.close();
	}
	
	public void findParcelsByUsername(String username) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findByUsername", Customer.class);
		List<Customer> matchedCustomers = query.setParameter("customerUsername", username).getResultList();
		matchedCustomers.forEach(m -> m.getParcelDetails());
		em.close();
	}

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
