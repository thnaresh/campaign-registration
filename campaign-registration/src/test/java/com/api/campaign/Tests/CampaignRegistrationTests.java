package com.api.campaign.Tests;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.api.campaign.entity.CampaignRegistration;
import com.api.campaign.repo.CampaignRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CampaignRegistrationTests {

	@Autowired
	private CampaignRepository campaignrepository;

	private CampaignRegistration campaignregistration, campregistr;

	@BeforeEach
	public void setup() {
		campaignregistration = new CampaignRegistration();
		campaignregistration.setCampaignid(1);
		campaignregistration.setCampaignName("Vehicle campaign Registration");
		campaignregistration.setDescription("for new Campaign Approval");
		campaignregistration.setNotificationNumber("23");
		campaignregistration.setCompensation(20600);
		campaignregistration.setCampaignApproval(true);
		
		  campregistr = new CampaignRegistration(); campregistr.setCampaignid(2);
		  campregistr.setCampaignName("Vehicle campaign Registration");
		  campregistr.setDescription("for new Campaign Approval");
		  campregistr.setNotificationNumber("32"); campregistr.setCompensation(20600);
		  campregistr.setCampaignApproval(true);
		 
	}

	// JUnit test for save campaign registration
	@DisplayName("JUnit test for save campaign registration")
	@Test
	public void Test_addCampaignregistration() {
		// when - action or the behaviour that we are going test
		CampaignRegistration savedcampaign = campaignrepository.saveAndFlush(campaignregistration);

		// then - verify the output
		assertThat(savedcampaign).isNotNull();
		assertThat(savedcampaign.getCampaignid()).isEqualTo(1);
	}

	// JUnit test for get all campaign registrations
	@DisplayName("JUnit test for get all campaign registrations")
	@Test
	public void Test_getAllCampaignRegistrations() {
		// given - precondition or setup

//	        campaignrepository.save(campaignregistration);
//	        campaignrepository.save(campregistr);

		// when - action or the behaviour that we are going test
		List<CampaignRegistration> campList = campaignrepository.findAll();

		// then - verify the output
		assertThat(campList).isNotNull();
		assertThat(campList.size()).isEqualTo(10);

	}

	// JUnit test for get employee by id operation
	@DisplayName("JUnit test for get campaign registration by id ")
	@Test
	public void Test_getCampaignRegistrationById() {

		campaignrepository.save(campregistr);

		CampaignRegistration campDB = campaignrepository.findById(campregistr.getCampaignid()).get();

		// then - verify the output
		assertThat(campDB).isNotNull();
	}

	// JUnit test for update campaign registration
	@DisplayName("JUnit test for update campaign registration")
	@Test
	public void Test_updateCampaignRegistration() {
 
		campaignrepository.save(campaignregistration);

		CampaignRegistration savedcampid = campaignrepository.findById(campaignregistration.getCampaignid()).get();
		savedcampid.setCampaignkind(34);
		savedcampid.setCampaignApproval(true);
		CampaignRegistration updatedcampid = campaignrepository.save(savedcampid);

		// then - verify the output
		assertThat(updatedcampid.getCampaignkind()).isEqualTo(34);
		assertThat(updatedcampid.getCampaignApproval()).isEqualTo(true);
	}

	// JUnit test for delete campaign registration
	@DisplayName("JUnit test for delete campaign registration")
	@Test
	public void Test_deleteCampaignRegistration() {

		campaignrepository.save(campregistr);

		campaignrepository.deleteById(campregistr.getCampaignid());
		Optional<CampaignRegistration> campOptional = campaignrepository.findById(campregistr.getCampaignid());

		// then - verify the output
		assertThat(campOptional).isEmpty();
	}
}
