package com.fdmgroup.parceltracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.parceltracking.model.Status;
import com.fdmgroup.parceltracking.service.StatusService;

@Controller
public class StatusController {
	
	@Autowired
	private StatusService statusService;
	
	@GetMapping("/statuses")
	public String statusPage() {
		return "statuscreation";
	}
	
	@GetMapping("/statuses/success")
	public String statusCreationSuccessPage() {
		return "statuscreationsuccess";
	}
	
	@GetMapping("/statuses/delete")
	public String statusDeletionPage() {
		return "statusdeletion";
	}
	
	@GetMapping("/statuses/delete/success")
	public String statusDeletionSuccessPage() {
		return "statusdeletionsuccess";
	}
	
	@PostMapping("/statuses")
	public String createStatus(@RequestParam("status") String statusName) {
		Status tempStatus = new Status(statusName);
		if (statusService.statusInDatabase(statusName)) {
			System.out.println("Status exists");
			return "statuses";
		}
		else {
			statusService.persist(tempStatus);
			System.out.println("Status successfully added to Database");
			return "redirect:/statuses/success";
		}
	}
	
	@PostMapping("/statuses/delete")
	public String deleteLocation(Model model,@RequestParam("statusName") String statusName) {
		Status tempStatus = statusService.findByStatus(statusName);
		model.addAttribute("tempLocation", tempStatus);
		if (statusService.statusInDatabase(statusName)) {
			statusService.deleteById(tempStatus.getStatusId());
			System.out.println("Status successfully deleted from Database");
			return "redirect:/statuses/delete/success";
		}
		else {
			System.out.println("Status does not exist");
			return "redirect:/statuses/delete";
		}
	}
	
}	
