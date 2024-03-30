package com.fdmgroup.parceltracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.parceltracking.model.Customer;
import com.fdmgroup.parceltracking.model.Location;
import com.fdmgroup.parceltracking.model.Parcel;
import com.fdmgroup.parceltracking.model.Status;
import com.fdmgroup.parceltracking.service.LocationService;
import com.fdmgroup.parceltracking.service.ParcelService;
import com.fdmgroup.parceltracking.service.StatusService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ParcelController {
	
	@Autowired
	private ParcelService parcelService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private StatusService statusService;
	
	@GetMapping("/dashboard/parcels")
	public String parcelPage() {
		return "parcel";
	}
	
	@GetMapping("/dashboard/parcels/success")
	public String createParcel() {
		return "parcelcreated";
	}
	
	@GetMapping("/dashboard/parcels/1")
	public String parcel1Page() {
		return "parcel1";
	}
	
	@GetMapping("/dashboard/parcels/2")
	public String parcel2Page() {
		return "parcel2";
	}
	
	@GetMapping("/dashboard/parcels/3")
	public String parcel3Page() {
		return "parcel3";
	}
	
	@GetMapping("/dashboard/parcels/4")
	public String parcel4Page() {
		return "parcel4";
	}
	
	@PostMapping("/dashboard/parcels/1")
	public String purchaseParcel1(HttpSession session, Model model) {
		Customer tempCustomer = (Customer) session.getAttribute("loggedUser");
		Location tempLocation = locationService.findByLocation("Singapore", "Singapore");
		Status tempStatus = statusService.findByStatus("Shipping from Origin");
		Parcel tempParcel = new Parcel(tempCustomer,tempLocation,tempStatus);
		parcelService.persist(tempParcel);
		model.addAttribute("tempParcel",tempParcel);
		System.out.println("Parcel created and added to Customer");
		return "parcelcreated";
	}	
	
	@PostMapping("/dashboard/parcels/2")
	public String purchaseParcel2(HttpSession session, Model model) {
		Customer tempCustomer = (Customer) session.getAttribute("loggedUser");
		Location tempLocation = locationService.findByLocation("Singapore", "Singapore");
		Status tempStatus = statusService.findByStatus("Reaching Local Facility");
		Parcel tempParcel = new Parcel(tempCustomer,tempLocation,tempStatus);
		parcelService.persist(tempParcel);
		model.addAttribute("tempParcel",tempParcel);
		System.out.println("Parcel created and added to Customer");
		return "parcelcreated";
	}	
	
	@PostMapping("/dashboard/parcels/3")
	public String purchaseParcel3(HttpSession session, Model model) {
		Customer tempCustomer = (Customer) session.getAttribute("loggedUser");
		Location tempLocation = locationService.findByLocation("Singapore", "Singapore");
		Status tempStatus = statusService.findByStatus("Out for delivery");
		Parcel tempParcel = new Parcel(tempCustomer,tempLocation,tempStatus);
		parcelService.persist(tempParcel);
		model.addAttribute("tempParcel",tempParcel);
		System.out.println("Parcel created and added to Customer");
		return "parcelcreated";
	}	
	
	@PostMapping("/dashboard/parcels/4")
	public String purchaseParcel4(HttpSession session, Model model) {
		Customer tempCustomer = (Customer) session.getAttribute("loggedUser");
		Location tempLocation = locationService.findByLocation("China", "Shenzhen");
		Status tempStatus = statusService.findByStatus("Shipping from Origin");
		Parcel tempParcel = new Parcel(tempCustomer,tempLocation,tempStatus);
		parcelService.persist(tempParcel);
		model.addAttribute("tempParcel",tempParcel);
		System.out.println("Parcel created and added to Customer");
		return "parcelcreated";
	}	
}
