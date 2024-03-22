package javaEnterpriseSoloProject;

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

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@ExtendWith(MockitoExtension.class)
class StatusRepositoryTest {
	
	private StatusRepository statusRepository;
	@Mock
	private EntityManagerFactory mockEmf;
	@Mock
	private EntityManager mockEm;
	@Mock
	private EntityTransaction mockEt;
	@Mock
	private Status mockStatus;
	
	@BeforeEach
	void setUp() {
		statusRepository = new StatusRepository(mockEmf);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
	}
	
	@Test
	@DisplayName("Test to check that persist method saves location to database")
	void test1() {
		when(mockEm.getTransaction()).thenReturn(mockEt);
		statusRepository.persist(mockStatus);
		
		InOrder order = inOrder(mockEmf,mockEm,mockEt);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockStatus);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
		
	@Test
	@DisplayName("Test for update method when location exists in database")
	void test2() {
		int id = 8;
		Status status = new Status("Shipping from Origin");
		status.setStatusId(id);
		when(mockEm.find(Status.class, id)).thenReturn(mockStatus);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		statusRepository.update(status);
				
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockStatus);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Status.class, id);
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockStatus).updateDetails(status);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test for update method when location does not exist in database")
	void test3() {
		int id = 33;
		Status status = new Status("Shipping from Origin");
		status.setStatusId(id);
		
		statusRepository.update(status);
				
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Status.class, id);
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		verify(mockEm, never()).getTransaction();
		verify(mockEt, never()).begin();
		verify(mockStatus, never()).updateDetails(status);
		verify(mockEt, never()).commit();
	}
	
	@Test
	@DisplayName("Test for deleteById method when location exists in database")
	void test4() {
		int id = 9;
		Status status = new Status("Shipping from Origin");
		status.setStatusId(id);
		when(mockEm.find(Status.class, id)).thenReturn(status);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		statusRepository.deleteById(id);
				
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Status.class, id);
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).remove(status);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test for deleteById method when location does not exist in database")
	void test5() {
		int id = 26;
		Status status = new Status("Shipping from Origin");
		status.setStatusId(id);
		
		statusRepository.deleteById(id);
				
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Status.class, id);
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		verify(mockEm, never()).getTransaction();
		verify(mockEt, never()).begin();
		verify(mockEm, never()).remove(any(Status.class));
		verify(mockEt, never()).commit();
	}

}
