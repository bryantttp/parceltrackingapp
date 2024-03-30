package com.fdmgroup.parceltracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.parceltracking.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

}
