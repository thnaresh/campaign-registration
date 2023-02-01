package com.api.campaign.Tests;

import static org.assertj.core.api.Assertions.entry;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.campaign.CampaignRegistrationApiApplication;
import com.api.campaign.entity.CampaignRegistration;
import com.api.campaign.repo.CampaignRepository;
import com.api.campaign.serviceimpl.CampaignServiceImpl;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = CampaignRegistrationApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CampaignControllerTest {

	@LocalServerPort
	private int localServerPort;

	@Autowired
	private CampaignServiceImpl campaignService;

	@MockBean
	private CampaignRepository campaignRepository;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testCampaignRegisterationSuccess() throws URISyntaxException {

		final String baseUrl = "http://localhost:" + localServerPort + "/api/campaigns/insert";
		URI uri = new URI(baseUrl);

		Map<String, String> map = Map.ofEntries(entry("en", "message in english"), entry("ja", "Japanese message"),
				entry("ch", "Chinese sentence"));

//		CampaignRegistration campaignRegistration = new CampaignRegistration(2, 1001, "ford", "ford", "Vehical Details",
//				map, 1, LocalDate.now(), LocalDate.now(), 90, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 81, 82, "83",
//				84, 85, 86, 87, 88, 89, 90, LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
//				LocalDate.now(), Boolean.FALSE, LocalDate.now(), LocalDate.now());

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

//		HttpEntity<CampaignRegistration> request = new HttpEntity<>(campaignRegistration, headers);

//		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		// Verify request succeed
//		Assertions.assertEquals(201, result.getStatusCodeValue());
	}

	@Test
	public void testgetAllCampaignRegistrationsSuccess() throws Exception {

		final String baseUrl = "http://localhost:" + localServerPort + "/api/campaigns/getAll";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		//String expected = "{\"id\":1,\"name\":\"Rajesh Bhojwani\",\"description\":\"Class 10\"}";

		//JSONAssert.assertEquals(expected, response.getBody(), false);
		// Verify request succeed
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

}
