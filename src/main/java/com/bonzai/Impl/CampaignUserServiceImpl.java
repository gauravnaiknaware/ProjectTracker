package com.bonzai.Impl;

import com.bonzai.model.CampaignUser;
import com.bonzai.repository.CampaignUserRepository;
import com.bonzai.services.CampaignAssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaurav on 7/6/17.
 */
@Service
public class CampaignUserServiceImpl implements CampaignAssigneeService {
    @Autowired
    CampaignUserRepository campaignUserRepository;


    @Override
    public CampaignUser save(CampaignUser entity) {
        return campaignUserRepository.save(entity);
    }

    @Override
    public CampaignUser getById(Serializable id) {
        return campaignUserRepository.findOne((Long)id);
    }

    @Override
    public List<CampaignUser> getAll() {
        return campaignUserRepository.findAll();
    }

    @Override
    public void delete(Serializable id) {
        campaignUserRepository.delete((Long)id);
    }
}
