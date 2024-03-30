package com.fdmgroup.parceltracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.parceltracking.model.Customer;
import com.fdmgroup.parceltracking.model.Location;
import com.fdmgroup.parceltracking.model.Parcel;
import com.fdmgroup.parceltracking.model.Status;
import com.fdmgroup.parceltracking.service.CustomerService;
import com.fdmgroup.parceltracking.service.LocationService;
import com.fdmgroup.parceltracking.service.ParcelService;
import com.fdmgroup.parceltracking.service.StatusService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ParcelController {
	
	@Autowired
	private ParcelService parcelService;
	
	@Autowired
	private CustomerService customerService;
	
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
	public String createParcel1(@PathVariable("id") long customerId, Model model) {
		Customer tempCustomer = customerService.findCustomerById(customerId);
		Location tempLocation = locationService.findByLocation("Singapore", "Singapore");
		Status tempStatus = statusService.findByStatus("Shipping from Origin");
		Parcel tempParcel = new Parcel(tempCustomer,tempLocation,tempStatus);
		parcelService.persist(tempParcel);
		model.addAttribute("tempParcel",tempParcel);
		System.out.println("Parcel created");
		return "parcel1";
	}
	
	@PutMapping("/dashboard/parcels/1")
	public String purchaseParcel1(HttpSession session, Model model) {
		Customer tempCustomer = (Customer) session.getAttribute("loggedUser");
		Parcel tempParcel = (Parcel) session.getAttribute("tempParcel");
		tempCustomer.setParcels(tempParcel);
		parcelService.update(tempParcel);
		customerService.update(tempCustomer);
		model.addAttribute("tempParcel", tempParcel);
		System.out.println("Parcel added to Customer");
		return "redirect:/parcelcreated";
	}
	
	@PutMapping("/dashboard/parcels/2")
	public String createParcel2(HttpSession session) {
		Parcel tempParcel = new Parcel((Customer) session.getAttribute("loggedUser"),null,null);
		parcelService.persist(tempParcel);
		return "parcelcreated";
	}
	
	@PutMapping("/dashboard/parcels/3")
	public String createParcel3(HttpSession session) {
		Parcel tempParcel = new Parcel((Customer) session.getAttribute("loggedUser"),null,null);
		parcelService.persist(tempParcel);
		return "parcelcreated";
	}
	
	@PutMapping("/dashboard/parcels/4")
	public String createParcel4(HttpSession session) {
		Parcel tempParcel = new Parcel((Customer) session.getAttribute("loggedUser"),null,null);
		parcelService.persist(tempParcel);
		return "parcelcreated";
	}
}
