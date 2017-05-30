package com.bonzai.converters;

import com.bonzai.dto.CampaignUserDto;
import com.bonzai.model.Campaign;
import com.bonzai.model.CampaignUser;
import com.bonzai.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaurav on 8/6/17.
 */
public class CampaignUserConverter {

    public static CampaignUser convertToCampaignUser(CampaignUserDto campaignUserDto){
        CampaignUser campaignUser = new CampaignUser();
        Campaign campaign = new Campaign();
        User user = new User();

        campaign.setId(campaignUserDto.getCampaignId());
        user.setId(campaignUserDto.getUserId());

        campaignUser.setId(campaignUserDto.getId());
        campaignUser.setCampaign(campaign);
        campaignUser.setUser(user);

        return campaignUser;
    }

    public static CampaignUserDto convertToDto(CampaignUser campaignUser) {
        CampaignUserDto campaignUserDto = new CampaignUserDto();
        campaignUserDto.setId(campaignUser.getId());

        Campaign campaign = new Campaign();
        campaign.setId(campaignUser.getCampaign().getId());
        campaign.setName(campaignUser.getCampaign().getName());
        campaign.setDate(campaignUser.getCampaign().getDate());
        campaign.setZone(campaignUser.getCampaign().getZone());
        campaign.setBuildEntry(campaignUser.getCampaign().getBuildEntry());
        campaign.setClientName(campaignUser.getCampaign().getClientName());
        campaign.setDueDate(campaignUser.getCampaign().getDueDate());
        campaign.setState(campaignUser.getCampaign().getState());
        campaign.setStatus(campaignUser.getCampaign().getStatus());
        campaign.setPriority(campaignUser.getCampaign().getPriority());
        campaign.setTeam(campaignUser.getCampaign().getTeam());
        campaign.setDescription(campaignUser.getCampaign().getDescription());

        campaignUserDto.setCampaign(campaign);

        User user = new User();
        user.setId(campaignUser.getUser().getId());
        user.setName(campaignUser.getUser().getName());
        user.setRole(campaignUser.getUser().getRole());
        user.setTeam(campaignUser.getUser().getTeam());

        campaignUserDto.setUser(user);

        return campaignUserDto;
    }

    public static List<CampaignUserDto> convertToListCampaignUserDto(List<CampaignUser> campaignUsers) {
        List<CampaignUserDto> campaignUserDtos = new ArrayList<CampaignUserDto>();

        for ( CampaignUser campaignUser : campaignUsers) {
            CampaignUserDto campaignUserDto = new CampaignUserDto();
            campaignUserDto.setId(campaignUser.getId());

            Campaign campaign = new Campaign();
            campaign.setId(campaignUser.getCampaign().getId());
            campaign.setName(campaignUser.getCampaign().getName());
            campaign.setDate(campaignUser.getCampaign().getDate());
            campaign.setZone(campaignUser.getCampaign().getZone());
            campaign.setBuildEntry(campaignUser.getCampaign().getBuildEntry());
            campaign.setClientName(campaignUser.getCampaign().getClientName());
            campaign.setDueDate(campaignUser.getCampaign().getDueDate());
            campaign.setState(campaignUser.getCampaign().getState());
            campaign.setStatus(campaignUser.getCampaign().getStatus());
            campaign.setPriority(campaignUser.getCampaign().getPriority());
            campaign.setTeam(campaignUser.getCampaign().getTeam());
            campaign.setDescription(campaignUser.getCampaign().getDescription());

            campaignUserDto.setCampaign(campaign);

            User user = new User();
            user.setId(campaignUser.getUser().getId());
            user.setName(campaignUser.getUser().getName());
            user.setRole(campaignUser.getUser().getRole());
            user.setTeam(campaignUser.getUser().getTeam());

            campaignUserDto.setUser(user);

            campaignUserDtos.add(campaignUserDto);
        }

        return campaignUserDtos;
    }
}
