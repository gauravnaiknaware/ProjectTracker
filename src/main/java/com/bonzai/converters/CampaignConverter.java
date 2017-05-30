package com.bonzai.converters;

import com.bonzai.dto.CampaignDto;
import com.bonzai.model.Campaign;
import com.bonzai.model.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav on 18/6/17.
 */
public class CampaignConverter {

    final static Logger log = Logger.getLogger(CampaignConverter.class);
    public static CampaignDto convertToDto(Campaign campaign) {
        CampaignDto campaignDto = new CampaignDto();
        campaignDto.setId(campaign.getId());
        campaignDto.setName(campaign.getName());
        campaignDto.setDate(campaign.getDate());
        campaignDto.setZone(campaign.getZone());
        campaignDto.setBuildEntry(campaign.getBuildEntry());
        campaignDto.setClientName(campaign.getClientName());
        campaignDto.setDueDate(campaign.getDueDate());
        campaignDto.setState(campaign.getState());
        campaignDto.setStatus(campaign.getStatus());
        campaignDto.setPriority(campaign.getPriority());
        campaignDto.setTeam(campaign.getTeam());
        campaignDto.setDescription(campaign.getDescription());
        campaignDto.setTask(campaign.getTask());

        if (campaign.getUser() != null) {
            User user = new User();
            user.setId(campaign.getUser().getId());
            user.setName(campaign.getUser().getName());
            user.setRole(campaign.getUser().getRole());
            user.setTeam(campaign.getUser().getTeam());

            campaignDto.setUser(user);
        }

        return campaignDto;
    }

    public static List<CampaignDto> convertToListDto(List<Campaign> campaigns) {

        List<CampaignDto> campaignDtos = new ArrayList<CampaignDto>();

        for (Campaign campaign : campaigns) {
            CampaignDto campaignDto = new CampaignDto();
            campaignDto.setId(campaign.getId());
            campaignDto.setName(campaign.getName());
            campaignDto.setDate(campaign.getDate());
            campaignDto.setZone(campaign.getZone());
            campaignDto.setBuildEntry(campaign.getBuildEntry());
            campaignDto.setClientName(campaign.getClientName());
            campaignDto.setDueDate(campaign.getDueDate());
            campaignDto.setState(campaign.getState());
            campaignDto.setStatus(campaign.getStatus());
            campaignDto.setPriority(campaign.getPriority());
            campaignDto.setTeam(campaign.getTeam());
            campaignDto.setDescription(campaign.getDescription());
            campaignDto.setTask(campaign.getTask());

        if (campaign.getUser() != null) {
            User user = new User();
            user.setId(campaign.getUser().getId());
            user.setName(campaign.getUser().getName());
            user.setRole(campaign.getUser().getRole());
            user.setTeam(campaign.getUser().getTeam());

            campaignDto.setUser(user);
        }
            campaignDtos.add(campaignDto);
        }
        return campaignDtos;
    }

    public static Campaign convertToCampaign(CampaignDto campaignDto) {
        Campaign campaign = new Campaign();
        campaign.setId(campaignDto.getId());
        campaign.setName(campaignDto.getName());
        campaign.setDate(campaignDto.getDate());
        campaign.setZone(campaignDto.getZone());
        campaign.setBuildEntry(campaignDto.getBuildEntry());
        campaign.setClientName(campaignDto.getClientName());
        campaign.setDueDate(campaignDto.getDueDate());
        campaign.setState(campaignDto.getState());
        campaign.setStatus(campaignDto.getStatus());
        campaign.setPriority(campaignDto.getPriority());
        campaign.setTeam(campaignDto.getTeam());
        campaign.setDescription(campaignDto.getDescription());
        campaign.setTask(campaignDto.getTask());

        Long l =  campaignDto.getUserId();
        if ( l != null ) {
            User user = new User();
            user.setId(campaignDto.getUserId());
            campaign.setUser(user);
        } else {
            campaign.setUser(null);
        }

        return campaign;
    }

}
