package com.fdmgroup.parceltracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.parceltracking.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query("select c from Customer c where c.username = :customerUsername")
	Optional<Customer> findByUsername(@Param("customerUsername") String username);
}
