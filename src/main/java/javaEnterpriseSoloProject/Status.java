/**
 * 
 * Status.java
 * - This file represents the POJO of the Status entity, where the Status ID
 * is the primary key and statusName as an attribute
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
import jakarta.persistence.Table;

@Entity
@Table(name = "StatusOfParcels")
public class Status {
	@Id
	@GeneratedValue
	@Column(name = "Status ID")
	private int statusId;
	
	@Column(name = "Status")
	private String statusName;
	
	/**
	 * 
	 * Custom constructor of the Status entity
	 * 
	 * @param statusName Name of the Status
	 */
	public Status(String statusName) {
		this.statusName = statusName;
	}
	
	/**
	 * 
	 * Generic getter method that returns the ID of the Status
	 * 
	 * @return int that represents the ID of the Status
	 */
	public int getStatusId() {
		return statusId;
	}
	
	/**
	 * 
	 * Generic setter method that sets the ID of the Status object
	 * 
	 * @param statusId ID of the Status object
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	/**
	 * 
	 * Generic getter method that returns the name of the Status object
	 * 
	 * @return String that represents the name of the status object
	 */
	public String getStatusName() {
		return statusName;
	}
	
	/**
	 * 
	 * Generic setter method that sets the name of the Status object
	 * 
	 * @param statusName Name of the Status object
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	/**
	 * 
	 * This method updates the Status object using another Status object
	 * 
	 * @param status Status object that will be used to update the current 
	 * Status object
	 */
	public void updateDetails(Status status) {
		setStatusName(status.getStatusName());
	}
}
