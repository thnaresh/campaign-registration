package com.api.campaign.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.campaign.entity.CampaignRegistration;
import com.api.campaign.exception.ResourceNotFoundException;
import com.api.campaign.repo.CampaignRepository;
import com.api.campaign.service.CampaignService;

@Service
public class CampaignServiceImpl implements CampaignService {

	private CampaignRepository campaignrepository;

	public CampaignServiceImpl(CampaignRepository campaignrepository) {
		super();
		this.campaignrepository = campaignrepository;
	}

	@Override
	public CampaignRegistration saveCampaignRegistration(CampaignRegistration CampaignRegistration) {
		return campaignrepository.save(CampaignRegistration);
	}

	@Override
	public List<CampaignRegistration> getAllCampaignRegistrations() {
		return campaignrepository.findAll();
	}

	@Override
	public CampaignRegistration getCampaignRegistrationById(Integer id) {
		return campaignrepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("CampaignRegistration", "Id", id));

	}

	@Override
	public CampaignRegistration updateCampaignRegistration(CampaignRegistration campaignregistration, Integer id) {

		CampaignRegistration result = campaignrepository.save(campaignregistration);
		return result;
	}

	@Override
	public void deleteCampaignRegistration(Integer id) {
		
		campaignrepository.deleteById(id);
	}

}
