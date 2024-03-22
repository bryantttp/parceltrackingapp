package javaEnterpriseSoloProject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class LocationRepository {
	
	private EntityManagerFactory emf;

	public LocationRepository(EntityManagerFactory emf) {
		this.setEmf(emf);
	}
	public EntityManagerFactory getEmf() {
		return emf;
	}
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void persist(Location location) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(location);
		et.commit();
		em.close();
	}

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
