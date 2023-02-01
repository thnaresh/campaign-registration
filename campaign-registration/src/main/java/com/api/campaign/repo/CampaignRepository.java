package com.api.campaign.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.campaign.entity.CampaignRegistration;

public interface CampaignRepository extends JpaRepository<CampaignRegistration, Integer>{

	
	

}
