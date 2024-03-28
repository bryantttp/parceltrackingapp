package com.fdmgroup.parceltracking;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.parceltracking.model.Parcel;
import com.fdmgroup.parceltracking.repository.ParcelRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@ExtendWith(MockitoExtension.class)
class ParcelRepositoryTest {
	
	private ParcelRepository parcelRepository;
	@Mock
	private EntityManagerFactory mockEmf;
	@Mock
	private EntityManager mockEm;
	@Mock
	private EntityTransaction mockEt;
	@Mock
	private Parcel mockParcel;
	
	@BeforeEach
	void setUp() {
		parcelRepository = new ParcelRepository(mockEmf);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
	}
	
	@Test
	@DisplayName("Test to check that persist method saves parcel to database")
	void test1() {
		when(mockEm.getTransaction()).thenReturn(mockEt);
		parcelRepository.persist(mockParcel);
		
		InOrder order = inOrder(mockEmf,mockEm,mockEt);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockParcel);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
		
	@Test
	@DisplayName("Test for update method when parcel exists in database")
	void test2() {
		long id = 1L;
		Parcel parcel = new Parcel();
		parcel.setParcelId(id);
		when(mockEm.find(Parcel.class, id)).thenReturn(mockParcel);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		parcelRepository.update(parcel);
				
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockParcel);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Parcel.class, id);
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockParcel).updateDetails(parcel);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test for update method when parcel does not exist in database")
	void test3() {
		long id = 50L;
		Parcel parcel = new Parcel();
		parcel.setParcelId(id);
		
		parcelRepository.update(parcel);
				
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Parcel.class, id);
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		verify(mockEm, never()).getTransaction();
		verify(mockEt, never()).begin();
		verify(mockParcel, never()).updateDetails(parcel);
		verify(mockEt, never()).commit();
	}
	
	@Test
	@DisplayName("Test for deleteById method when parcel exists in database")
	void test4() {
		long id = 10L;
		Parcel parcel = new Parcel();
		parcel.setParcelId(id);
		when(mockEm.find(Parcel.class, id)).thenReturn(parcel);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		parcelRepository.deleteById(id);
				
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Parcel.class, id);
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).remove(parcel);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test for deleteById method when parcel does not exist in database")
	void test5() {
		long id = 75L;
		Parcel parcel = new Parcel();
		parcel.setParcelId(id);
		
		parcelRepository.deleteById(id);
				
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Parcel.class, id);
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		verify(mockEm, never()).getTransaction();
		verify(mockEt, never()).begin();
		verify(mockEm, never()).remove(any(Parcel.class));
		verify(mockEt, never()).commit();
	}
}
