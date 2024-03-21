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
	
	public Status(String statusName) {
		this.statusName = statusName;
	}
	
	public int getStatusId() {
		return statusId;
	}
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatus(String statusName) {
		this.statusName = statusName;
	}
	
}
