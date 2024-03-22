package javaEnterpriseSoloProject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class ParcelRepository {
	
	private EntityManagerFactory emf;

	public ParcelRepository(EntityManagerFactory emf) {
		this.setEmf(emf);
	}
	public EntityManagerFactory getEmf() {
		return emf;
	}
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void persist(Parcel parcel) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(parcel);
		et.commit();
		em.close();
	}

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
