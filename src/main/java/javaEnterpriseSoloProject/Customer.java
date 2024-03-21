package javaEnterpriseSoloProject;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "Customer.findByUsername", query = "select c from Customer c where c.username = :customerUsername") })
@Table(name = "listOfCustomers")
public class Customer{
	@Id
	@GeneratedValue
	@Column(name = "Customer ID")
	private long customerId;
	
	@OneToMany(mappedBy = "customer")
	private List<Parcel> parcels = new ArrayList<>();
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "First Name")	
	private String firstName;
	
	@Column(name = "Last Name")
	private String lastName;
	
	public Customer(String address, String username, String password, String firstName, String lastName) {
		this.setAddress(address);
		this.setUsername(username);
		this.setPassword(password);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	public long getId() {
		return customerId;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setParcels(Parcel parcel) {
		this.parcels.add(parcel);
	}
	
	public void getParcels() {
		for (Parcel parcel : parcels) {
			System.out.println(parcel.getLocation());
			System.out.println(parcel.getStatus());
		}
	}
	
	public void updateDetails(Customer customer) {
		setAddress(customer.getAddress());
		setUsername(customer.getUsername());
		setPassword(customer.getPassword());
		setFirstName(customer.getFirstName());
		setLastName(customer.getLastName());
	}
}
