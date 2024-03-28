/**
 * 
 * Parcel.java
 * - This file represents the POJO of the Parcel entity, where the Parcel ID
 * is the primary key and shares a Bidirectional ManyToOne relationship with 
 * Customer entity and a ManyToOne Unidirectional relationship with Location and 
 * Status entities
 * 
 * @author Bryant Pang
 * @version 0.0.1
 * @since 22/03/24
 * 
 */
package javaEnterpriseSoloProject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "listOfParcels")
public class Parcel {
	@Id
	@GeneratedValue
	@Column(name = "Order ID")
	private long parcelId;
	
	@ManyToOne
	@JoinColumn(name = "FK Customer ID", nullable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "FK Location ID", nullable = false)
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "FK Status ID", nullable = false)
	private Status status;
	
	/**
	 * 
	 * Custom constructor of the Parcel entity
	 * 
	 * @param customer Customer that ordered the parcel
	 * @param location Location of the parcel
	 * @param status Status of the parcel
	 */
	public Parcel(Customer customer, Location location, Status status) {
		this.customer = customer;
		customer.setParcels(this);
		this.location = location;
		this.status = status;
	}
	
	/**
	 * 
	 * Default constructor of the Parcel entity
	 * 
	 */
	public Parcel() {
		return;
	}
	
	/**
	 * 
	 * Generic getter method that returns the ID of the Parcel
	 * 
	 * @return Long that represents the ID of the Parcel
	 */
	public long getParcelId() {
		return parcelId;
	}
	
	/**
	 * 
	 * Generic setter method that sets the ID of the Parcel
	 * 
	 * @param parcelId ID of the Parcel
	 */
	public void setParcelId(long parcelId) {
		this.parcelId = parcelId;
	}
	
	/**
	 * 
	 * Generic getter method that returns the location of the Parcel
	 * 
	 * @return Location that represents the location of the Parcel
	 */
	public Location getLocation() {
		return this.location;
	}
	
	/**
	 * 
	 * Generic setter method that sets the location of the Parcel
	 * 
	 * @param location Location of the Parcel
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * 
	 * Generic getter method that returns the status of the Parcel
	 * 
	 * @return Status that represents the status of the Parcel
	 */
	public Status getStatus() {
		return this.status;
	}
	
	/**
	 * 
	 * Generic setter method that sets the status of the Parcel
	 * 
	 * @param status Status of the Parcel
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * 
	 * This method returns the full details of the status of the Parcel
	 * 
	 * @return String that represents the full details of the status of the Parcel
	 */
	public String getStatusDetails() {
		return "Parcel " + Long.toString(parcelId) + " is currently " + status.getStatusName();
	}
	
	/**
	 * 
	 * This method returns the full details of the location of the Parcel
	 * 
	 * @return String that represents the full details of the location of the Parcel
	 */
	public String getLocationDetails() {
		return "Parcel " + Long.toString(parcelId) + " is currently in " + location.getCity() + ", " + location.getCountry();
	}
	
	/**
	 * 
	 * This method updates the location and status of the Parcel
	 * 
	 * @param parcel New Status and Location of the Parcel
	 */
	public void updateDetails(Parcel parcel) {
		setLocation(parcel.getLocation());
		setStatus(parcel.getStatus());
	}
	
}
