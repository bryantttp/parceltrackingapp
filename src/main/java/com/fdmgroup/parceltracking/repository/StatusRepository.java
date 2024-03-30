package com.fdmgroup.parceltracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.parceltracking.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{

}
