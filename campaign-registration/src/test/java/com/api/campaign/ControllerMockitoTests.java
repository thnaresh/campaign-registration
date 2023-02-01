package com.api.campaign;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.campaign.controller.CampaignController;
import com.api.campaign.entity.CampaignRegistration;
import com.api.campaign.service.CampaignService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = { ControllerMockitoTests.class })
public class ControllerMockitoTests {
	@Mock
	CampaignService campaignservice;

	@InjectMocks
	CampaignController campaignController;

	List<CampaignRegistration> cr;

	CampaignRegistration cpr;

	@Test
	@Order(1)
	public void test_getAllCampaignRegistrations() {

		cr = new ArrayList<CampaignRegistration>();
		cr.add(new CampaignRegistration(1, "successfully updated", 8, null, "", null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null));
		cr.add(new CampaignRegistration(2, "successfully updated", 9, null, "", null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null));

		when(campaignservice.getAllCampaignRegistrations()).thenReturn(cr);// mocking
		ResponseEntity<List<CampaignRegistration>> res = campaignController.getAllCampaignRegistrations();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2, res.getBody().size());
	}

	@Test
	@Order(2)
	public void test_getCampaignRegistrationById() {

		cpr = new CampaignRegistration(4, "successfully updated", 8, null, "", null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null);

		int cmpid = 4;

		when(campaignservice.getCampaignRegistrationById(cmpid)).thenReturn(cpr);// mocking
		ResponseEntity<CampaignRegistration> res = campaignController.getCampaignRegistrationById(cmpid);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(cmpid, res.getBody().getCampaignid());
	}

	@Test
	@Order(3)
	public void test_addCampaignRegistration() {

		cpr = new CampaignRegistration(5, "successfully updated", 16, null, "", null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null);

		when(campaignservice.saveCampaignRegistration(cpr)).thenReturn(cpr);// mocking
		ResponseEntity<CampaignRegistration> res = campaignController.saveCampaignRegistration(cpr);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(cpr, res.getBody());

	}

	@Test
	@Order(4)
	public void test_updateCampaignRegistration() {

		cpr = new CampaignRegistration(44, "successfully updated", 10, null, "", null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null);
		int cmpgnid = 44;

		when(campaignservice.getCampaignRegistrationById(cmpgnid)).thenReturn(cpr);// mocking
		when(campaignservice.updateCampaignRegistration(cpr, cmpgnid)).thenReturn(cpr);// mocking
		ResponseEntity<CampaignRegistration> res = campaignController.updateCampaignRegistration(cmpgnid, cpr);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(44, res.getBody().getCampaignid());

	}

	@Test
	@Order(5)
	public void test_deleteCampaignRegistrationById() {

		cpr = new CampaignRegistration(48, "successfully updated", 90, null, "", null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null);
		int cmpgnid = cpr.getCampaignid();

//		when(campaignservice.getCampaignRegistrationById(cmpgnid)).thenReturn(cpr);// mocking
		 ResponseEntity<String> res  = campaignController.deleteCampaignRegistration(cmpgnid);
			//campaignservice.deleteCampaignRegistration(cmpgnid);
			verify(campaignservice, times(1)).deleteCampaignRegistration(48);
		 assertEquals(HttpStatus.OK, res.getStatusCode());
		 

	}
}