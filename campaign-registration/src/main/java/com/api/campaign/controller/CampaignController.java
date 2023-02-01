package com.api.campaign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.campaign.entity.CampaignRegistration;
import com.api.campaign.service.CampaignService;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

	private CampaignService campaignservice;

	public CampaignController(CampaignService campaignservice) {
		this.campaignservice = campaignservice;
		System.out.println("testing");
	}

	// build create campaign REST API
	@PostMapping("/insert")
	public ResponseEntity<CampaignRegistration> saveCampaignRegistration(
			@RequestBody CampaignRegistration campaignregistration) {
		return new ResponseEntity<CampaignRegistration>(campaignservice.saveCampaignRegistration(campaignregistration),
				HttpStatus.CREATED);
	}

	// build get all CampaignRegistrations REST API
	@GetMapping("/getAll")
	public ResponseEntity<List<CampaignRegistration>> getAllCampaignRegistrations() {
		try {
			List<CampaignRegistration> cpr = campaignservice.getAllCampaignRegistrations();
			return new ResponseEntity<List<CampaignRegistration>>(cpr, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	// build get CampaignRegistration by id REST API
	// http://localhost:8586/api/campaigns/1
	@GetMapping("{id}")
	public ResponseEntity<CampaignRegistration> getCampaignRegistrationById(
			@PathVariable("id") Integer campaignregistrationid) {
		return new ResponseEntity<CampaignRegistration>(
				campaignservice.getCampaignRegistrationById(campaignregistrationid), HttpStatus.FOUND);
	}

	// build update CampaignRegistration REST API
	// http://localhost:8586/api/campaigns/1
	@PutMapping("{id}")
	public ResponseEntity<CampaignRegistration> updateCampaignRegistration(@PathVariable("id") Integer id,
			@RequestBody CampaignRegistration campaignregistration) {
		return new ResponseEntity<CampaignRegistration>(
				campaignservice.updateCampaignRegistration(campaignregistration, id), HttpStatus.OK);
	}

	// build delete CampaignRegistration REST API
	// http://localhost:8586/api/campaigns/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCampaignRegistration(@PathVariable("id") Integer id) {

		// delete CampaignRegistration from DB
		campaignservice.deleteCampaignRegistration(id);

		return new ResponseEntity<String>("CampaignRegistration deleted successfully!! with  the id   " + " " + id,
				HttpStatus.OK);
	}

}
