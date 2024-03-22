package javaEnterpriseSoloProject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parcelLocation")
public class Location {
	@Id
	@GeneratedValue
	@Column(name = "Location ID")
	private int locationId;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "City")
	private String city;
	
	public Location(String country, String city) {
		this.country = country;
		this.city = city;
	}
	
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void updateDetails(Location location) {
		setCountry(location.getCountry());
		setCity(location.getCity());
	}
}
