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
	@JoinColumn(name = "FK Customer ID")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "FK Location ID")
	private Location location;
	
	@ManyToOne
	@JoinColumn(name = "FK Status ID")
	private Status status;
	
	public Parcel(Customer customer, Location location, Status status) {
		this.customer = customer;
		customer.setParcels(this);
		this.location = location;
		this.status = status;
	}
	
	public Parcel() {
		return;
	}
	
	public void setParcelId(long parcelId) {
		this.parcelId = parcelId;
	}
	
	public long getParcelId() {
		return parcelId;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getStatusDetails() {
		return "Parcel " + Long.toString(parcelId) + " is currently " + status.getStatusName();
	}
	
	public String getLocationDetails() {
		return "Parcel " + Long.toString(parcelId) + " is currently in " + location.getCity() + ", " + location.getCountry();
	}
	
	public void updateDetails(Parcel parcel) {
		setLocation(parcel.getLocation());
		setStatus(parcel.getStatus());
	}
	
}
