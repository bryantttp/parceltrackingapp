package com.fdmgroup.parceltracking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.parceltracking.model.Customer;
import com.fdmgroup.parceltracking.service.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/index")
	public String welcomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage(HttpSession session, Model model) {
		if (session.getAttribute("loggedUser") != null) {
			Customer returnedCustomer = (Customer) session.getAttribute("loggedUser");
			model.addAttribute(returnedCustomer);
			return "dashboard";
		}
		else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logout")
	public String logoutPage(HttpSession session) {
		System.out.println("Customer has logged out.");
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("customers/{id}")
	public String profilePage(@PathVariable("id") long customerId, Model model) {
		System.out.println("Redirecting to Customer's profile page");
		Customer customer = customerService.findCustomerById(customerId);
		model.addAttribute("customer",customer);
		return "profile";
	}
	
	@PostMapping("/register")
	public String processRegistration(@RequestParam("username") String username, @RequestParam("password") String password){
		Customer customer = new Customer(null,username,password,null,null);
		if(customerService.usernameInDatabase(username)) {
			System.out.println("Customer exists");
			return("register");
		}
		else {
			customerService.persist(customer);
			System.out.println("Username : " + username + " | Password : " + password);
			System.out.println("Customer registration succeeded");
			return("redirect:/login");
		}
	}
	
	@PostMapping("/login")
	public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {
		if(customerService.passwordChecker(username, password)) {
			System.out.println("Authentication was successful!");
            session.setAttribute("loggedUser", customerService.findCustomerByUsername(username));
            Customer returnedCustomer = customerService.findCustomerByUsername(username);
            model.addAttribute("customer",returnedCustomer);
			return "redirect:/dashboard";
		}
		else {
			System.out.println("Authentication failed!");
			return "login";
		}
	}
}
