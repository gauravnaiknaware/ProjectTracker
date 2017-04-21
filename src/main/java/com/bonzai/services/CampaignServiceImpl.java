package com.bonzai.services;

import java.io.Serializable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonzai.model.Campaign;
import com.bonzai.repository.CampaignRepository;

@Service
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	CampaignRepository campaignRepository;
	
	@Override
	public Campaign save(Campaign entity) {
		return campaignRepository.save(entity);
	}

	@Override
	public Campaign getById(Serializable id) {
		return campaignRepository.findOne((Long)id);
	}

	@Override
	public List<Campaign> getAll() {
		return campaignRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		campaignRepository.delete((Long)id);
	}

}
