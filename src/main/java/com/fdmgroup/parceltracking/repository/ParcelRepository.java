package com.fdmgroup.parceltracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.parceltracking.model.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long>{
	
}
