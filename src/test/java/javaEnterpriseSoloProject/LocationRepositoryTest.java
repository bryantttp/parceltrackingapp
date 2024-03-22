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
class LocationRepositoryTest {
	
	private LocationRepository locationRepository;
	@Mock
	private EntityManagerFactory mockEmf;
	@Mock
	private EntityManager mockEm;
	@Mock
	private EntityTransaction mockEt;
	@Mock
	private Location mockLocation;
	
	@BeforeEach
	void setUp() {
		locationRepository = new LocationRepository(mockEmf);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
	}
	
	@Test
	@DisplayName("Test to check that persist method saves location to database")
	void test1() {
		when(mockEm.getTransaction()).thenReturn(mockEt);
		locationRepository.persist(mockLocation);
		
		InOrder order = inOrder(mockEmf,mockEm,mockEt);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(mockLocation);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
		
	@Test
	@DisplayName("Test for update method when location exists in database")
	void test2() {
		int id = 1;
		Location location = new Location("Singapore", "Singapore");
		location.setLocationId(id);
		when(mockEm.find(Location.class, id)).thenReturn(mockLocation);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		locationRepository.update(location);
				
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockLocation);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Location.class, id);
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockLocation).updateDetails(location);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test for update method when location does not exist in database")
	void test3() {
		int id = 15;
		Location location = new Location("Singapore", "Singapore");
		location.setLocationId(id);
		
		locationRepository.update(location);
				
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Location.class, id);
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		verify(mockEm, never()).getTransaction();
		verify(mockEt, never()).begin();
		verify(mockLocation, never()).updateDetails(location);
		verify(mockEt, never()).commit();
	}
	
	@Test
	@DisplayName("Test for deleteById method when location exists in database")
	void test4() {
		int id = 6;
		Location location = new Location("Singapore", "Singapore");
		location.setLocationId(id);
		when(mockEm.find(Location.class, id)).thenReturn(location);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		locationRepository.deleteById(id);
				
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Location.class, id);
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).remove(location);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test for deleteById method when location does not exist in database")
	void test5() {
		int id = 45;
		Location location = new Location("Singapore", "Singapore");
		location.setLocationId(id);
		
		locationRepository.deleteById(id);
				
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Location.class, id);
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		verify(mockEm, never()).getTransaction();
		verify(mockEt, never()).begin();
		verify(mockEm, never()).remove(any(Location.class));
		verify(mockEt, never()).commit();
	}
}
