package javaEnterpriseSoloProject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class StatusRepository {
	private EntityManagerFactory emf;

	public StatusRepository(EntityManagerFactory emf) {
		this.setEmf(emf);
	}
	public EntityManagerFactory getEmf() {
		return emf;
	}
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void persist(Status status) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(status);
		et.commit();
		em.close();
	}

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
