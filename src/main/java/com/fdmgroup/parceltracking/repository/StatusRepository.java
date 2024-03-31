package com.fdmgroup.parceltracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.parceltracking.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{
	
	@Query(value="select * from status_of_parcels s where s.status = :statusName",nativeQuery = true)
	Optional<Status> findByStatus(@Param("statusName") String status);

	
}
