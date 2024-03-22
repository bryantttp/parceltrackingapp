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
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryTest {
	
	private CustomerRepository customerRepository;
	
	@Mock
	private EntityManagerFactory mockEmf;
	@Mock
	private EntityManager mockEm;
	@Mock
	private EntityTransaction mockEt;
	@Mock
	private Customer mockCustomer;
	@Mock
	private TypedQuery<Customer> mockQuery;
	
	@BeforeEach
	void setUp() {
		customerRepository = new CustomerRepository(mockEmf);
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
	}
	
	@Test
	@DisplayName("Test to check that persist method saves customer to database")
	void test1() {
		Customer customer = new Customer("BLK 64 YUNG KUANG ROAD, #01-113","john.doe","johndoe123","John","Doe");
		when(mockEm.getTransaction()).thenReturn(mockEt);
		customerRepository.persist(customer);
		
		InOrder order = inOrder(mockEmf,mockEm,mockEt);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).persist(customer);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test to check that findParcelsByUsername method retrieves parcel details from database")
	void test2() {
		Customer customer = new Customer("BLK 64 YUNG KUANG ROAD, #01-113","john.doe","johndoe123","John","Doe");
		when(mockEm.createNamedQuery("Customer.findByUsername", Customer.class)).thenReturn(mockQuery);
		when(mockQuery.setParameter("customerUsername",customer.getUsername())).thenReturn(mockQuery);
		customerRepository.findParcelsByUsername(customer.getUsername());
		
		InOrder order = inOrder(mockEmf,mockEm,mockQuery);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).createNamedQuery("Customer.findByUsername", Customer.class);
		order.verify(mockQuery).setParameter("customerUsername",customer.getUsername());
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		
	}
	
	@Test
	@DisplayName("Test for update method when customer exists in database")
	void test3() {
		long id = 3L;
		Customer customer = new Customer("BLK 64 YUNG KUANG ROAD, #01-113","john.doe","johndoe123","John","Doe");
		customer.setId(id);
		when(mockEm.find(Customer.class, id)).thenReturn(mockCustomer);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		customerRepository.update(customer);
				
		InOrder order = inOrder(mockEmf, mockEm, mockEt, mockCustomer);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Customer.class, id);
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockCustomer).updateDetails(customer);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test for update method when customer does not exist in database")
	void test4() {
		long id = 70L;
		Customer customer = new Customer("BLK 64 YUNG KUANG ROAD, #01-113","john.doe","johndoe123","John","Doe");
		customer.setId(id);
		customerRepository.update(customer);
				
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Customer.class, id);
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		verify(mockEm, never()).getTransaction();
		verify(mockEt, never()).begin();
		verify(mockCustomer, never()).updateDetails(customer);
		verify(mockEt, never()).commit();
	}
	
	@Test
	@DisplayName("Test for deleteById method when customer exists in database")
	void test5() {
		long id = 4L;
		Customer customer = new Customer("BLK 64 YUNG KUANG ROAD, #01-113","john.doe","johndoe123","John","Doe");
		customer.setId(id);
		when(mockEm.find(Customer.class, id)).thenReturn(customer);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		
		customerRepository.deleteById(id);
				
		InOrder order = inOrder(mockEmf, mockEm, mockEt);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Customer.class, id);
		order.verify(mockEm).getTransaction();
		order.verify(mockEt).begin();
		order.verify(mockEm).remove(customer);
		order.verify(mockEt).commit();
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
	}
	
	@Test
	@DisplayName("Test for deleteById method when customer does not exist in database")
	void test6() {
		long id = 88L;
		Customer customer = new Customer("BLK 64 YUNG KUANG ROAD, #01-113","john.doe","johndoe123","John","Doe");
		customer.setId(id);
		
		customerRepository.deleteById(id);
				
		InOrder order = inOrder(mockEmf, mockEm);
		order.verify(mockEmf).createEntityManager();
		order.verify(mockEm).find(Customer.class, id);
		order.verify(mockEm).close();
		order.verifyNoMoreInteractions();
		verify(mockEm, never()).getTransaction();
		verify(mockEt, never()).begin();
		verify(mockEm, never()).remove(any(Customer.class));
		verify(mockEt, never()).commit();
	}
	

	
}
