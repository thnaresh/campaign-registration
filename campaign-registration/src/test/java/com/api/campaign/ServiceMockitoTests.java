package com.api.campaign;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.campaign.entity.CampaignRegistration;
import com.api.campaign.repo.CampaignRepository;
import com.api.campaign.serviceimpl.CampaignServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = { ServiceMockitoTests.class })
public class ServiceMockitoTests {
	@Mock
	CampaignRepository camprepo;

	@InjectMocks
	CampaignServiceImpl campservice;

	@Test
	@Order(1)
	public void test_getAllCampaignRegistrations() {

		CampaignRegistration campregister = new CampaignRegistration();
		campregister.setCampaignName("vehicle camp Register");
		campregister.setDescription("pre Approval");
		campregister.setExecutionDate("2023-01-23");
		CampaignRegistration cr = new CampaignRegistration(6, "successfully deleted", 8, null, "", null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		List<CampaignRegistration> listcampregister = new ArrayList<CampaignRegistration>();
		listcampregister.add(campregister);
		listcampregister.add(cr);

		when(camprepo.findAll()).thenReturn(listcampregister);// mocking
		assertEquals(2, campservice.getAllCampaignRegistrations().size());

	}

	@Test
	@Order(2)
	public void test_getCampaignRegistrationById() {
		Optional<CampaignRegistration> cr = Optional
				.ofNullable(new CampaignRegistration(11, "success", null, null, "", null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null));
		when(camprepo.findById(1)).thenReturn(cr);
		assertEquals(11, campservice.getCampaignRegistrationById(1).getCampaignid());
	}

	@Test
	@Order(3)
	public void test_saveCampaignRegistration() {

		CampaignRegistration campregister = new CampaignRegistration();
		campregister.setCampaignName("vehicle camp Register");
		campregister.setEvaluationCampaignid(4);
		campregister.setDescription("pre Approval");
		campregister.setExecutionDate("2023-08-25");
		when(camprepo.save(campregister)).thenReturn(campregister);// mocking
		assertEquals(campregister, campservice.saveCampaignRegistration(campregister));
	}

	@Test
	@Order(4)
	public void test_updateCampaignRegistration() {

		CampaignRegistration cr = new CampaignRegistration(1, "successfully updated", 8, null, "", null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		when(camprepo.save(cr)).thenReturn((cr));// mocking
		Integer campaignid = 1;
		assertEquals(cr, campservice.updateCampaignRegistration(cr, campaignid));

	}

	@Test
	@Order(5)
	public void test_deleteCampaignRegistration() {
		CampaignRegistration cr = new CampaignRegistration(5, "successfully deleted", 8, null, "", null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		Integer campid = cr.getCampaignid();
		campservice.deleteCampaignRegistration(campid);
		verify(camprepo, times(1)).deleteById(5);

	}

}
