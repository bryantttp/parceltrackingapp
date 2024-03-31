package com.fdmgroup.parceltracking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.parceltracking.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{
	
	@Query("select l from Location l where l.country = :countryName and l.city = :cityName" )
	Optional<Location> findByCityAndCountry(@Param("countryName") String country, @Param("cityName") String city);

}
