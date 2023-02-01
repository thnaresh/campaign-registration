package com.api.campaign.service;

import java.util.List;

import com.api.campaign.entity.CampaignRegistration;

public interface CampaignService {
	CampaignRegistration saveCampaignRegistration(CampaignRegistration CampaignRegistration);
	List<CampaignRegistration> getAllCampaignRegistrations();
	CampaignRegistration getCampaignRegistrationById(Integer id);
	CampaignRegistration updateCampaignRegistration(CampaignRegistration CampaignRegistration, Integer id);
	void deleteCampaignRegistration(Integer id);

}
